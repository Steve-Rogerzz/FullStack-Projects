package com.stitch.eventhub.controller;

import com.stitch.eventhub.entity.Event;
import com.stitch.eventhub.entity.User;
import com.stitch.eventhub.repository.UserRepository;
import com.stitch.eventhub.service.EventService;
import com.stitch.eventhub.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final EventService eventService;
    private final RegistrationService registrationService;
    private final UserRepository userRepository;

    @GetMapping("/")
    public String home(@org.springframework.web.bind.annotation.RequestParam(required = false) String query, Model model) {
        List<Event> events;
        if (query != null && !query.isEmpty()) {
            events = eventService.searchEvents(query);
        } else {
            events = eventService.getAllEvents();
        }
        model.addAttribute("events", events);
        model.addAttribute("query", query);
        model.addAttribute("showHero", true);
        return "student_dashboard";
    }

    @GetMapping("/events")
    public String browseEvents(@org.springframework.web.bind.annotation.RequestParam(required = false) String query, Model model) {
        List<Event> events;
        if (query != null && !query.isEmpty()) {
            events = eventService.searchEvents(query);
        } else {
            events = eventService.getAllEvents();
        }
        model.addAttribute("events", events);
        model.addAttribute("query", query);
        model.addAttribute("showHero", false);
        return "student_dashboard";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/events/{id}")
    public String eventDetails(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        eventService.getEventById(id).ifPresent(event -> {
            model.addAttribute("event", event);
            if (userDetails != null) {
                userRepository.findByUsername(userDetails.getUsername()).ifPresent(user -> {
                    model.addAttribute("isRegistered", registrationService.isUserRegistered(user.getId(), id));
                });
            }
        });
        return "event_details";
    }

    @PostMapping("/events/{id}/register")
    public String registerForEvent(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "redirect:/login";
        
        Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
        if (userOpt.isPresent()) {
            registrationService.registerUserForEvent(userOpt.get(), id);
        }
        return "redirect:/events/" + id;
    }

    @PostMapping("/events/{id}/cancel")
    public String cancelRegistration(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return "redirect:/login";
        
        Optional<User> userOpt = userRepository.findByUsername(userDetails.getUsername());
        if (userOpt.isPresent()) {
            registrationService.cancelRegistration(userOpt.get(), id);
        }
        return "redirect:/events/" + id;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "admin_management";
    }

    @GetMapping("/admin/events/new")
    public String createEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "create_edit_event";
    }

    @GetMapping("/admin/events/edit/{id}")
    public String editEventForm(@PathVariable Long id, Model model) {
        eventService.getEventById(id).ifPresent(event -> model.addAttribute("event", event));
        return "create_edit_event";
    }
}
