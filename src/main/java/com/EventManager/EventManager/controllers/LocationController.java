package com.EventManager.EventManager.controllers;


import com.EventManager.EventManager.domain.dto.LocationDto;
import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.mappers.Mapper;
import com.EventManager.EventManager.services.intefaces.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LocationController {

    private final LocationService locationService;
    private final Mapper<LocationEntity, LocationDto> locationMapper;

    public LocationController(LocationService locationService, Mapper<LocationEntity, LocationDto> locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping(path = "/location")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        LocationEntity locationEntity = locationMapper.mapFrom(locationDto);

        LocationEntity savedLocationEntity = locationService.save(locationEntity);

        return new ResponseEntity<>(locationMapper.mapTo(savedLocationEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/location/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("locationId") Long locationId) {
        Optional<LocationEntity> foundLocation = locationService.findOne(locationId);

        return foundLocation.map(LocationEntity -> {
            LocationDto locationDto = locationMapper.mapTo(LocationEntity);
            return new ResponseEntity<>(
                    locationDto,
                    HttpStatus.OK
            );
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/location/{locationId}")
    public ResponseEntity<LocationDto> locationFullUpdate(@PathVariable("locationId") Long locationId, @RequestBody LocationDto locationDto) {

        locationDto.setLocationId(locationId);
        LocationEntity locationEntity = locationMapper.mapFrom(locationDto);
        LocationEntity savedLocationEntity = locationService.save(locationEntity);

        return new ResponseEntity<>(locationMapper.mapTo(savedLocationEntity), HttpStatus.OK);
    }
}
