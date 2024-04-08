package com.EventManager.EventManager.services.intefaces;

import com.EventManager.EventManager.domain.entities.LocationEntity;

import java.util.Optional;

public interface LocationService {
    LocationEntity save(LocationEntity locationEntity);

    Optional<LocationEntity> findOne(Long locationId);
}
