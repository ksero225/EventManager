package com.EventManager.EventManager.services.intefaces;

import com.EventManager.EventManager.domain.entities.LocationEntity;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    LocationEntity save(LocationEntity locationEntity);

    Optional<LocationEntity> findOne(Long locationId);

    List<LocationEntity> findAll();

    LocationEntity partialUpdate(LocationEntity locationEntity);

    void deleteById(Long locationId);
}
