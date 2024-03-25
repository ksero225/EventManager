package com.EventManager.EventManager.services.implementations;

import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.repositories.ParticipantRepository;
import com.EventManager.EventManager.services.intefaces.ParticipantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Override
    public List<ParticipantEntity> findAllParticipantsByEventName() {
        return null;
    }
}
