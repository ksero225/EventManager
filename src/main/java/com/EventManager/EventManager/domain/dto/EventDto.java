package com.EventManager.EventManager.domain.dto;

import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long eventId;
    private String eventName;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private LocationEntity eventLocalization;
    private BigDecimal eventTicketPrice;
    private List<ParticipantEntity> participants = new ArrayList<>();
}
