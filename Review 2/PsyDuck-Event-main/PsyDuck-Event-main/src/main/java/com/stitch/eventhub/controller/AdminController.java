package com.stitch.eventhub.controller;

import com.stitch.eventhub.entity.Event;
import com.stitch.eventhub.service.EventService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final EventService eventService;

    @PostMapping("/events/save")
    public String saveEvent(@ModelAttribute Event event) {
        eventService.saveEvent(event);
        return "redirect:/admin";
    }

    @GetMapping("/events/export")
    public void exportEvents(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; file=events_export.csv");

        List<Event> events = eventService.getAllEvents();
        PrintWriter writer = response.getWriter();

        // CSV Header
        writer.println("ID,Title,Category,Department,Date,Status,Registered,Capacity");

        for (Event event : events) {
            writer.printf("%d,\"%s\",\"%s\",\"%s\",%s,%s,%d,%d%n",
                    event.getId(),
                    event.getTitle().replace("\"", "\"\""),
                    event.getCategory(),
                    event.getDepartment(),
                    event.getDate(),
                    event.getStatus(),
                    event.getRegisteredCount(),
                    event.getCapacity());
        }
        writer.flush();
    }
}
