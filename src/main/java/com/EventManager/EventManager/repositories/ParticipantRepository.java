package com.EventManager.EventManager.repositories;

import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
