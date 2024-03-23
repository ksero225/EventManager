package com.EventManager.EventManager.controllersTests;


import com.EventManager.EventManager.TestDataUtilities;
import com.EventManager.EventManager.domain.dto.EventDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.services.intefaces.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class EventControllerTests {

    private final EventService eventService;
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Autowired
    public EventControllerTests(EventService eventService, MockMvc mockMvc) {
        this.eventService = eventService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testThatCreateEventSuccessfullyReturnsHttp201Created() throws Exception {
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();

        String eventEntityJson = objectMapper.writeValueAsString(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateEventSuccessfullyReturnsHttp409Conflict() throws Exception {
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();

        String eventEntityJson = objectMapper.writeValueAsString(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

        mockMvc.perform(
                MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isConflict()
        );
    }

    @Test
    public void testThatCreateEventSuccessfullyReturnsHttp400BadRequest() throws Exception {
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();
        LocalDateTime endDate = LocalDateTime.of(2023, 1, 1, 23, 59);
        eventEntity.setEventEndDate(endDate);
        String eventEntityJson = objectMapper.writeValueAsString(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void testThatCreateEventSuccessfullyReturnsCreatedEvent() throws Exception {
        EventDto eventDto = TestDataUtilities.createTestEventDtoA();

        String eventEntityJson = objectMapper.writeValueAsString(eventDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventName").value("testA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventStartDate").value("2024-01-01T01:01")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventEndDate").value("2024-01-01T23:59")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventLocalization").isEmpty()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.eventTicketPrice").value(12.20)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.participants").isEmpty()
        );
    }

    @Test
    public void testThatListEventsReturnsEvent() throws Exception {
        EventEntity eventEntityA = TestDataUtilities.createTestEventEntityA();
        eventService.save(eventEntityA);
        EventEntity eventEntityB = TestDataUtilities.createTestEventEntityB();
        eventService.save(eventEntityB);
        EventEntity eventEntityC = TestDataUtilities.createTestEventEntityC();
        eventService.save(eventEntityC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/event")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventName").value("testA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventStartDate").value("2024-01-01T01:01")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventEndDate").value("2024-01-01T23:59")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventLocalization").isEmpty()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].eventTicketPrice").value(12.20)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].participants").isEmpty()
        );
    }

    @Test
    public void testThatGetEventByIdReturnsHttpStatus200WhenEventExists() throws Exception {
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();
        eventService.save(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/event/" + eventEntity.getEventId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetEventByIdReturnsHttpStatus404WhenEventDoesntExists() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/event/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateEventReturnsHttpStatus404NotFound() throws Exception {

        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();
        String jsonEventEntity = objectMapper.writeValueAsString(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/event/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEventEntity)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateEventReturnsHttpStatus409Conflict() throws Exception {

        EventEntity eventEntityA = TestDataUtilities.createTestEventEntityA();
        eventService.save(eventEntityA);
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();
        String jsonEventEntity = objectMapper.writeValueAsString(eventEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/event/testA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonEventEntity)
        ).andExpect(
                MockMvcResultMatchers.status().isConflict()
        );

    }

    @Test
    public void testThatFullUpdateEventReturnsHttpStatus400BadRequest() throws Exception {
        EventEntity eventEntity = TestDataUtilities.createTestEventEntityA();
        eventService.save(eventEntity);

        EventDto eventDto = TestDataUtilities.createTestEventDtoA();
        LocalDateTime endDate = LocalDateTime.of(2023, 1, 1, 23, 59);
        eventDto.setEventEndDate(endDate);
        eventDto.setEventName("UPDATED");
        String eventEntityJson = objectMapper.writeValueAsString(eventDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/event/testA")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventEntityJson)
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }
}
