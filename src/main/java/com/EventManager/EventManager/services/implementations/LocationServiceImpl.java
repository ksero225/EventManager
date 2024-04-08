package com.EventManager.EventManager.services.implementations;

import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.repositories.LocationRepository;
import com.EventManager.EventManager.services.intefaces.LocationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<LocationEntity> findAll() {
        return new ArrayList<>(locationRepository.findAll());
    }

    @Override
    public LocationEntity partialUpdate(LocationEntity locationEntity) {
        Optional<LocationEntity> foundLocation = locationRepository.findById(locationEntity.getLocationId());

        return foundLocation.map(existingLocation -> {
            Optional.ofNullable(locationEntity.getLocationCity()).ifPresent(existingLocation::setLocationCity);
            Optional.ofNullable(locationEntity.getLocationCountry()).ifPresent(existingLocation::setLocationCountry);

            return locationRepository.save(existingLocation);
        }).orElseThrow(() -> new RuntimeException("Location not found"));
    }

    @Override
    public void deleteById(Long locationId) {
        locationRepository.deleteById(locationId);
    }
}
