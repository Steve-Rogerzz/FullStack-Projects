package com.stitch.eventhub.service;

import com.stitch.eventhub.entity.Event;
import com.stitch.eventhub.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event saveEvent(Event event) {
        if (event.getStatus() == null) {
            event.setStatus(Event.EventStatus.UPCOMING);
        }
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> searchEvents(String query) {
        return eventRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(query, query);
    }
}
