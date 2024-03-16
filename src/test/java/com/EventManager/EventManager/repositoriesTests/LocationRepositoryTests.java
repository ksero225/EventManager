package com.EventManager.EventManager.repositoriesTests;

import com.EventManager.EventManager.TestDataUtilities;
import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.repositories.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class LocationRepositoryTests {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationRepositoryTests(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Test
    public void testThatLocationCanBeCreatedAndRecalled() {
        LocationEntity location = TestDataUtilities.createTestLocationEntityA();
        locationRepository.save(location);

        Optional<LocationEntity> result = locationRepository.findById(location.getLocationId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(location);
    }

    @Test
    public void testThatMultipleLocationCanBeCreatedAndRecalled() {
        LocationEntity locationA = TestDataUtilities.createTestLocationEntityA();
        locationRepository.save(locationA);
        LocationEntity locationB = TestDataUtilities.createTestLocationEntityB();
        locationRepository.save(locationB);
        LocationEntity locationC = TestDataUtilities.createTestLocationEntityC();
        locationRepository.save(locationC);

        Iterable<LocationEntity> result = locationRepository.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        locationA,
                        locationB,
                        locationC
                );

    }

    @Test
    public void testThatLocationCanBeUpdated(){
        LocationEntity locationA = TestDataUtilities.createTestLocationEntityA();
        locationRepository.save(locationA);

        Optional<LocationEntity> result = locationRepository.findById(locationA.getLocationId());
        assertThat(result).isPresent();
        assertThat(result.get().getLocationCity()).isEqualTo("locationCityA");

        locationA.setLocationCity("UPDATED");

        locationRepository.save(locationA);

        result = locationRepository.findById(locationA.getLocationId());
        assertThat(result).isPresent();
        assertThat(result.get().getLocationCity()).isEqualTo("UPDATED");
    }

    @Test
    public void testThatLocationCanBeDeleted(){
        LocationEntity locationA = TestDataUtilities.createTestLocationEntityA();
        locationRepository.save(locationA);

        Optional<LocationEntity> result = locationRepository.findById(locationA.getLocationId());
        assertThat(result).isPresent();

        locationRepository.delete(locationA);
        result = locationRepository.findById(locationA.getLocationId());
        assertThat(result).isEmpty();
    }
}
