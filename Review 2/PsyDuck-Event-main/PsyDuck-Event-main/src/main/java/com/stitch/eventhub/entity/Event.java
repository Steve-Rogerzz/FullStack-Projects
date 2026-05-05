package com.stitch.eventhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    private String department;
    private String category;
    
    private int capacity;
    private int registeredCount;

    @Column(length = 1000)
    private String imageUrl;
    private String coordinator;
    
    @Column(length = 2000)
    private String schedule;
    
    @Column(length = 2000)
    private String plans;
    
    private String organizerContact;
    
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public enum EventStatus {
        UPCOMING, REGISTERED, FULL, PAST
    }
}
