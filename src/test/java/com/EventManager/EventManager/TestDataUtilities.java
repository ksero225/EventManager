package com.EventManager.EventManager;

import com.EventManager.EventManager.domain.dto.EventDto;
import com.EventManager.EventManager.domain.dto.ParticipantDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
                .eventTicketPrice(BigDecimal.valueOf(12.11))
                .participants(null)
                .build();
    }

    public static EventDto createTestEventDtoA(){
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 1, 1);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 1, 23, 59);

        return EventDto.builder()
                .eventId(1L)
                .eventName("testA")
                .eventStartDate(startDate)
                .eventEndDate(endDate)
                .eventLocalization(null)
                .eventTicketPrice(BigDecimal.valueOf(12.11))
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
                .eventTicketPrice(BigDecimal.valueOf(12.22))
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
                .eventTicketPrice(BigDecimal.valueOf(12.33))
                .participants(new HashSet<>())
                .build();
    }

    public static LocationEntity createTestLocationEntityA(){
        return LocationEntity.builder()
                .locationId(1L)
                .address("adresA")
                .locationCity("locationCityA")
                .locationCountry("locationCountryA")
                .build();
    }

    public static LocationEntity createTestLocationEntityB(){
        return LocationEntity.builder()
                .locationId(2L)
                .address("adresB")
                .locationCity("locationCityB")
                .locationCountry("locationCountryB")
                .build();
    }

    public static LocationEntity createTestLocationEntityC(){
        return LocationEntity.builder()
                .locationId(3L)
                .address("adresC")
                .locationCity("locationCityC")
                .locationCountry("locationCountryC")
                .build();
    }

    public static ParticipantEntity createTestParticipantEntityA(){
        return ParticipantEntity.builder()
                .participantId(1L)
                .participantName("participantNameA")
                .participantSurname("participantSurnameA")
                .event(null)
                .build();
    }

    public static ParticipantDto createTestParticipantDtoA(){
        return ParticipantDto.builder()
                .participantId(1L)
                .participantName("participantNameA")
                .participantSurname("participantSurnameA")
                .event(null)
                .build();
    }

    public static ParticipantEntity createTestParticipantEntityB(){
        return ParticipantEntity.builder()
                .participantId(2L)
                .participantName("participantNameB")
                .participantSurname("participantSurnameB")
                .event(null)
                .build();
    }

    public static ParticipantEntity createTestParticipantEntityC(){
        return ParticipantEntity.builder()
                .participantId(3L)
                .participantName("participantNameC")
                .participantSurname("participantSurnameC")
                .event(null)
                .build();
    }
}
