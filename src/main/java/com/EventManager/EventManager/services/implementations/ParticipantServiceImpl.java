package com.EventManager.EventManager.services.implementations;

import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.repositories.EventRepository;
import com.EventManager.EventManager.repositories.ParticipantRepository;
import com.EventManager.EventManager.services.intefaces.ParticipantService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    public ParticipantServiceImpl(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public ParticipantEntity save(ParticipantEntity participantEntity) {

        if (participantEntity.getEvent() != null) {
            if (!eventRepository.existsByEventName(participantEntity.getEvent().getEventName())) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Event not found"
                );
            }
        }

        return participantRepository.save(participantEntity);
    }

    @Override
    public Optional<ParticipantEntity> findOne(Long participantId) {
        return participantRepository.findById(participantId);
    }

    @Override
    public ParticipantEntity partialUpdate(ParticipantEntity participantEntity) {
        Optional<ParticipantEntity> foundParticipant = participantRepository.findById(participantEntity.getParticipantId());

        return foundParticipant.map(existingParticipant ->{
            Optional.ofNullable(participantEntity.getParticipantName()).ifPresent(existingParticipant::setParticipantName);
            Optional.ofNullable(participantEntity.getParticipantSurname()).ifPresent(existingParticipant::setParticipantSurname);
            Optional.ofNullable(participantEntity.getEvent()).ifPresent(existingParticipant::setEvent);

            return participantRepository.save(existingParticipant);
        }).orElseThrow(() -> new RuntimeException("Participant not found"));
    }
}
