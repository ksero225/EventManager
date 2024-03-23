package com.EventManager.EventManager.services.intefaces;

import com.EventManager.EventManager.domain.entities.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventService {
    EventEntity save(EventEntity eventEntity);

    boolean doesEventNameExists(String eventName);

    List<EventEntity> findAll();

    Optional<EventEntity> findOne(Long eventId);

    EventEntity partialUpdate(String eventName, EventEntity eventEntity);

    void delete(String eventName);
}
