package com.EventManager.EventManager;

import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.domain.entities.LocationEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

public class TestDataUtilities {
    public static EventEntity createTestEventEntityA(){
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 1, 1);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 1, 23, 59);

        return EventEntity.builder()
                .eventId(1L)
                .eventName("testA")
                .eventStartDate(startDate)
                .eventEndDate(endDate)
                .eventLocalization(null)
                .eventTicketPrice(12.20f)
                .participants(new HashSet<>())
                .build();
    }

    public static EventEntity createTestEventEntityB(){
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 2, 1, 1);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 2, 23, 59);

        return EventEntity.builder()
                .eventId(2L)
                .eventName("testB")
                .eventStartDate(startDate)
                .eventEndDate(endDate)
                .eventLocalization(null)
                .eventTicketPrice(12.50f)
                .participants(new HashSet<>())
                .build();
    }

    public static EventEntity createTestEventEntityC(){
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 3, 1, 1);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 2, 23, 59);

        return EventEntity.builder()
                .eventId(3L)
                .eventName("testC")
                .eventStartDate(startDate)
                .eventEndDate(endDate)
                .eventLocalization(null)
                .eventTicketPrice(13.50f)
                .participants(new HashSet<>())
                .build();
    }
}
