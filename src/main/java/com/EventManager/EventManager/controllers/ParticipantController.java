package com.EventManager.EventManager.controllers;

import com.EventManager.EventManager.domain.dto.ParticipantDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.mappers.Mapper;
import com.EventManager.EventManager.services.intefaces.EventService;
import com.EventManager.EventManager.services.intefaces.ParticipantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class ParticipantController {

    private final ParticipantService participantService;
    private final EventService eventService;
    private final Mapper<ParticipantEntity, ParticipantDto> participantMapper;

    public ParticipantController(ParticipantService participantService, EventService eventService, Mapper<ParticipantEntity, ParticipantDto> participantMapper) {
        this.participantService = participantService;
        this.eventService = eventService;
        this.participantMapper = participantMapper;
    }

    @GetMapping(path = "/participants/{participantId}")
    public ResponseEntity<ParticipantDto> getParticipantByParticipantId(@PathVariable("participantId") Long participantId){
        Optional<ParticipantEntity> foundParticipant = participantService.findOne(participantId);

        return foundParticipant.map(ParticipantEntity ->{
            ParticipantDto participantDto = participantMapper.mapTo(ParticipantEntity);
            return new ResponseEntity<>(
                    participantDto,
                    HttpStatus.OK
            );
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @GetMapping(path = "/event/participants/{eventName}")
    public List<ParticipantDto> listParticipantsOfEvent(@PathVariable("eventName") String eventName){
        Optional<EventEntity> foundEvent = eventService.findOne(eventName);
        if(foundEvent.isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Passed event does not exists"
            );
        }

        Set<ParticipantEntity> participants =  foundEvent.get().getParticipants();

        return participants.stream().map(participantMapper::mapTo).collect(Collectors.toList());
    }

    @PostMapping(path = "/participants")
    public ResponseEntity<ParticipantDto> createParticipant(@RequestBody ParticipantDto participantDto){
        ParticipantEntity participantEntity = participantMapper.mapFrom(participantDto);

        ParticipantEntity savedParticipantEntity = participantService.save(participantEntity);

        return new ResponseEntity<>(participantMapper.mapTo(savedParticipantEntity), HttpStatus.CREATED);
    }

    @PutMapping(path = "/participants/{participantId}")
    public ResponseEntity<ParticipantDto> fullUpdateParticipant(@PathVariable("participantId") Long participantId, @RequestBody ParticipantDto participantDto){

        participantDto.setParticipantId(participantId);

        ParticipantEntity participantEntity = participantMapper.mapFrom(participantDto);
        ParticipantEntity savedParticipantEntity = participantService.save(participantEntity);

        return new ResponseEntity<>(participantMapper.mapTo(savedParticipantEntity), HttpStatus.OK);
    }

    @PatchMapping(path = "/participants/{participantId}")
    public ResponseEntity<ParticipantDto> partialUpdateParticipant(@PathVariable("participantId") Long participantId, @RequestBody ParticipantDto participantDto){
        participantDto.setParticipantId(participantId);

        ParticipantEntity participantEntity = participantMapper.mapFrom(participantDto);
        ParticipantEntity savedParticipantEntity = participantService.partialUpdate(participantEntity);

        return new ResponseEntity<>(participantMapper.mapTo(savedParticipantEntity), HttpStatus.OK);
    }

}
