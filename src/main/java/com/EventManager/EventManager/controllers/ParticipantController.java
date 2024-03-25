package com.EventManager.EventManager.controllers;

import com.EventManager.EventManager.domain.dto.ParticipantDto;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.mappers.Mapper;
import com.EventManager.EventManager.services.intefaces.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ParticipantController {

    private final ParticipantService participantService;
    private final Mapper<ParticipantEntity, ParticipantDto> participantMapper;

    public ParticipantController(ParticipantService participantService, Mapper<ParticipantEntity, ParticipantDto> participantMapper) {
        this.participantService = participantService;
        this.participantMapper = participantMapper;
    }

//    @GetMapping(path = "/participants/{eventName}")
//    public List<ParticipantDto> listParticipantsOfEvent(@PathVariable("eventName") String eventName){
//        List<ParticipantEntity> participants = participantService.findAllParticipantsByEventName();
//
//    }
}
