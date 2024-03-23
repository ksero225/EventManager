package com.EventManager.EventManager.domain.dto;

import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long eventId;
    private String eventName;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventStartDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventEndDate;
    private LocationEntity eventLocalization;
    private BigDecimal eventTicketPrice;
    private Set<ParticipantEntity> participants = new HashSet<>();
}
