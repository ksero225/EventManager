package com.EventManager.EventManager.domain.dto;

import com.EventManager.EventManager.domain.entities.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDto {
    private Long participantId;
    private String participantName;
    private String participantSurname;
    private EventEntity event;
}


//TEST3
//TEST3
//TEST3
//TEST3
//TEST3

//TEST-BRANCH TEST

//TEST PULL-REQUEST