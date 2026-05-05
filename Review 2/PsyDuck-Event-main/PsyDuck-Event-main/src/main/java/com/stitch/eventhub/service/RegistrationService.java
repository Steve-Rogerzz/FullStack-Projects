package com.stitch.eventhub.service;

import com.stitch.eventhub.entity.Event;
import com.stitch.eventhub.entity.Registration;
import com.stitch.eventhub.entity.User;
import com.stitch.eventhub.repository.EventRepository;
import com.stitch.eventhub.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    @Transactional
    public String registerUserForEvent(User user, Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (registrationRepository.existsByUserIdAndEventId(user.getId(), eventId)) {
            return "ALREADY_REGISTERED";
        }

        if (event.getRegisteredCount() >= event.getCapacity()) {
            return "EVENT_FULL";
        }

        Registration registration = Registration.builder()
                .user(user)
                .event(event)
                .registrationTime(LocalDateTime.now())
                .build();

        registrationRepository.save(registration);
        
        event.setRegisteredCount(event.getRegisteredCount() + 1);
        if (event.getRegisteredCount() >= event.getCapacity()) {
            event.setStatus(Event.EventStatus.FULL);
        }
        eventRepository.save(event);

        return "SUCCESS";
    }

    @Transactional
    public void cancelRegistration(User user, Long eventId) {
        if (registrationRepository.existsByUserIdAndEventId(user.getId(), eventId)) {
            registrationRepository.deleteByUserIdAndEventId(user.getId(), eventId);
            
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Event not found"));
            
            if (event.getRegisteredCount() > 0) {
                event.setRegisteredCount(event.getRegisteredCount() - 1);
                if (event.getRegisteredCount() < event.getCapacity() && event.getStatus() == Event.EventStatus.FULL) {
                    event.setStatus(Event.EventStatus.UPCOMING);
                }
                eventRepository.save(event);
            }
        }
    }

    public boolean isUserRegistered(Long userId, Long eventId) {
        return registrationRepository.existsByUserIdAndEventId(userId, eventId);
    }
}
