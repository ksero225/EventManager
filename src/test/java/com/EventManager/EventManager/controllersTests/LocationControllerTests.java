package com.EventManager.EventManager.controllersTests;

import com.EventManager.EventManager.TestDataUtilities;
import com.EventManager.EventManager.domain.dto.LocationDto;
import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.repositories.LocationRepository;
import com.EventManager.EventManager.services.intefaces.LocationService;
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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureMockMvc
public class LocationControllerTests {

    private final MockMvc mockMvc;
    private final LocationService locationService;
    private final ObjectMapper objectMapper;

    @Autowired
    public LocationControllerTests(MockMvc mockMvc, LocationRepository locationRepository, LocationService locationService, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.locationService = locationService;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testThatGetLocationByLocationIdReturnsHttpStatus404NotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/location/999")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetLocationByLocationIdReturnsLocation() throws Exception {
        LocationEntity locationEntity = TestDataUtilities.createTestLocationEntityA();

        locationService.save(locationEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/location/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address").value("adresA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationCity").value("locationCityA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationCountry").value("locationCountryA")
        );
    }

    @Test
    public void testThatListLocationReturnsLocation() throws Exception {
        LocationEntity locationEntityA = TestDataUtilities.createTestLocationEntityA();
        locationService.save(locationEntityA);
        LocationEntity locationEntityB = TestDataUtilities.createTestLocationEntityA();
        locationService.save(locationEntityB);
        LocationEntity locationEntityC = TestDataUtilities.createTestLocationEntityA();
        locationService.save(locationEntityC);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/location")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].locationId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].address").value("adresA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].locationCity").value("locationCityA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].locationCountry").value("locationCountryA")
        );
    }

    @Test
    public void testThatFullUpdatedLocationReturnsHttpStatus404NotFound() throws Exception {
        LocationDto locationDto = TestDataUtilities.createTestLocationDtoA();
        String stringLocationDto = objectMapper.writeValueAsString(locationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/location/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringLocationDto)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateLocationReturnsUpdateLocation() throws Exception {
        LocationEntity locationEntity = TestDataUtilities.createTestLocationEntityA();
        locationService.save(locationEntity);

        LocationDto locationDto = TestDataUtilities.createTestLocationDtoA();
        locationDto.setLocationCity("UPDATED");
        String stringLocationDto = objectMapper.writeValueAsString(locationDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/location/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringLocationDto)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationId").value(1L)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.address").value("adresA")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationCity").value("UPDATED")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.locationCountry").value("locationCountryA")
        );
    }


}
