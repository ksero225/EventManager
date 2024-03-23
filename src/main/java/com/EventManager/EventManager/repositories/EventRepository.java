package com.EventManager.EventManager.repositories;

import com.EventManager.EventManager.domain.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    boolean existsByEventName(String eventName);

    Optional<EventEntity> findByEventName(String eventName);

    void deleteByEventName(String eventName);
}
