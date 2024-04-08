package com.EventManager.EventManager.services.implementations;

import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.repositories.LocationRepository;
import com.EventManager.EventManager.services.intefaces.LocationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public LocationEntity save(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }

    @Override
    public Optional<LocationEntity> findOne(Long locationId) {
        return locationRepository.findById(locationId);
    }
}
