package com.EventManager.EventManager.services.intefaces;

import com.EventManager.EventManager.domain.entities.ParticipantEntity;

import java.util.List;
import java.util.Optional;

public interface ParticipantService {
    ParticipantEntity save(ParticipantEntity participantEntity);

    Optional<ParticipantEntity> findOne(Long participantId);

    ParticipantEntity partialUpdate(ParticipantEntity participantEntity);

    void deleteById(Long participantId);
}
