package com.EventManager.EventManager.repositories;

import com.EventManager.EventManager.domain.entities.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
