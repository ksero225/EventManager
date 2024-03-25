package com.EventManager.EventManager.services.intefaces;

import com.EventManager.EventManager.domain.entities.ParticipantEntity;

import java.util.List;

public interface ParticipantService {
    List<ParticipantEntity> findAllParticipantsByEventName();
}
