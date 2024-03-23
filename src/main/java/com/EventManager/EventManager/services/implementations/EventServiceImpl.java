package com.EventManager.EventManager.services.implementations;

import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.repositories.EventRepository;
import com.EventManager.EventManager.services.intefaces.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventEntity save(EventEntity eventEntity) {
        return eventRepository.save(eventEntity);
    }

    @Override
    public List<EventEntity> findAll() {
        return new ArrayList<>(eventRepository.findAll());
    }

    @Override
    public Optional<EventEntity> findOne(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public EventEntity partialUpdate(String eventName, EventEntity eventEntity) {

        Optional<EventEntity> foundEvent = eventRepository.findByEventName(eventName);

        return foundEvent.map(existingWorker ->{
            Optional.ofNullable(eventEntity.getEventName()).ifPresent(existingWorker::setEventName);
            Optional.ofNullable(eventEntity.getEventStartDate()).ifPresent(existingWorker::setEventStartDate);
            Optional.ofNullable(eventEntity.getEventEndDate()).ifPresent(existingWorker::setEventEndDate);
            Optional.ofNullable(eventEntity.getEventLocalization()).ifPresent(existingWorker::setEventLocalization);
            Optional.ofNullable(eventEntity.getEventTicketPrice()).ifPresent(existingWorker::setEventTicketPrice);
            Optional.ofNullable(eventEntity.getParticipants()).ifPresent(existingWorker::setParticipants);

            return eventRepository.save(existingWorker);
        }).orElseThrow(() -> new RuntimeException("Event does not exists"));

    }

    @Override
    public void delete(String eventName) {
        eventRepository.deleteByEventName(eventName);
    }

    @Override
    public boolean doesEventNameExists(String eventName) {
        return eventRepository.existsByEventName(eventName);
    }
}
