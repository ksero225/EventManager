package com.EventManager.EventManager.repositories;

import com.EventManager.EventManager.domain.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
}
