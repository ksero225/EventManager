package com.EventManager.EventManager.controllers;

import com.EventManager.EventManager.domain.dto.EventDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.mappers.Mapper;
import com.EventManager.EventManager.services.intefaces.EventService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EventController {

    private final EventService eventService;

    private final Mapper<EventEntity, EventDto> eventMapper;

    public EventController(EventService eventService, Mapper<EventEntity, EventDto> eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }

    @PostMapping(path = "/event")
    public ResponseEntity<EventDto> createEvent(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) EventDto eventDto) {
        EventEntity eventEntity = eventMapper.mapFrom(eventDto);

        checkEventDtoValidation(eventDto);

        EventEntity savedEventEntity = eventService.save(eventEntity);
        return new ResponseEntity<>(eventMapper.mapTo(savedEventEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/event")
    public List<EventDto> listEvents() {
        List<EventEntity> events = eventService.findAll();
        if (events.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT,
                    "Theres no saved events in database"
            );
        }
        return events.stream().map(eventMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/event/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable("id") Long eventId) {
        Optional<EventEntity> foundEvent = eventService.findOne(eventId);

        return foundEvent.map(EventEntity -> {
            EventDto eventDto = eventMapper.mapTo(EventEntity);
            return new ResponseEntity<>(
                    eventDto,
                    HttpStatus.OK
            );
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PutMapping(path = "/event/{eventName}")
    public ResponseEntity<EventDto> fullUpdateEvent(@PathVariable("eventName") String eventName, @RequestBody EventDto eventDto) {

        checkEventDtoValidation(eventName, eventDto);

        eventDto.setEventName(eventName);

        EventEntity eventEntity = eventMapper.mapFrom(eventDto);
        EventEntity savedEventEntity = eventService.save(eventEntity);

        return new ResponseEntity<>(eventMapper.mapTo(savedEventEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/event/{eventName}")
    public ResponseEntity<EventDto> partialUpdateEvent(@PathVariable("eventName") String eventName, @RequestBody EventDto eventDto) {

        checkEventDtoValidation(eventName, eventDto);

        EventEntity eventEntity = eventMapper.mapFrom(eventDto);
        EventEntity savedEventEntity = eventService.partialUpdate(eventName, eventEntity);

        return new ResponseEntity<>(eventMapper.mapTo(savedEventEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/event/{eventName}")
    public ResponseEntity<Void> deleteWorkerByEventName(@PathVariable("eventName") String eventName) {
        if (eventService.doesEventNameExists(eventName)) {
            eventService.delete(eventName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private void checkEventDtoValidation(String eventName, EventDto eventDto) {
        EventEntity eventEntity = eventMapper.mapFrom(eventDto);

        if (!eventService.doesEventNameExists(eventName)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Passed event name does not exists in database"
            );
        }

        if (eventService.doesEventNameExists(eventEntity.getEventName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Event name passed in file already exists"
            );
        }

        if (eventDto.getEventStartDate().isAfter(eventDto.getEventEndDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Event end date is before start date"
            );
        }
    }

    private void checkEventDtoValidation(EventDto eventDto){

        EventEntity eventEntity = eventMapper.mapFrom(eventDto);

        if (eventService.doesEventNameExists(eventEntity.getEventName())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Event name passed in file already exists"
            );
        }

        if (eventDto.getEventStartDate().isAfter(eventDto.getEventEndDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Event end date is before start date"
            );
        }
    }

}
