package com.EventManager.EventManager.controllersTests;

import com.EventManager.EventManager.TestDataUtilities;
import com.EventManager.EventManager.domain.dto.ParticipantDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.services.intefaces.EventService;
import com.EventManager.EventManager.services.intefaces.ParticipantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ParticipantControllerTests {

    private final MockMvc mockMvc;
    private final ParticipantService participantService;
    private final EventService eventService;
    private final ObjectMapper objectMapper;

    @Autowired
    public ParticipantControllerTests(MockMvc mockMvc, ParticipantService participantService, EventService eventService) {
        this.mockMvc = mockMvc;
        this.participantService = participantService;
        this.eventService = eventService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatGetParticipantByParticipantIdReturnsHttpStatus404NotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/participants/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetParticipantByParticipantIdReturnsParticipant() throws Exception {
        ParticipantEntity participant = TestDataUtilities.createTestParticipantEntityA();
        EventEntity event = TestDataUtilities.createTestEventEntityA();
        participant.setEvent(event);

        participantService.save(participant);
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/participants/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.participantId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.participantName").value("participantNameA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.participantSurname").value("participantSurnameA")
        ).andReturn();
    }

    @Test
    public void testThatListParticipantOfEventReturnsParticipant() throws Exception {
        EventEntity event = TestDataUtilities.createTestEventEntityA();
        eventService.save(event);

        ParticipantEntity participantA = TestDataUtilities.createTestParticipantEntityA();
        participantA.setEvent(event);
        participantService.save(participantA);

        ParticipantEntity participantB = TestDataUtilities.createTestParticipantEntityB();
        participantB.setEvent(event);
        participantService.save(participantB);

        ParticipantEntity participantC = TestDataUtilities.createTestParticipantEntityC();
        participantC.setEvent(event);
        participantService.save(participantC);

        Set<ParticipantEntity> participants = new HashSet<>();
        participants.add(participantA);
        participants.add(participantB);
        participants.add(participantC);

        event.setParticipants(participants);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/event/participants/testA")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participantId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participantName").value("participantNameA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participantSurname").value("participantSurnameA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].participantId").value(2L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].participantName").value("participantNameB")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].participantSurname").value("participantSurnameB")
        );
    }
}
