package com.EventManager.EventManager.repositoriesTests;

import com.EventManager.EventManager.TestDataUtilities;

import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.repositories.EventRepository;
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
public class EventRepositoryTests {

    private final EventRepository eventRepository;

    @Autowired
    public EventRepositoryTests(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Test
    public void testThatEventCanBeCreatedAndRecalled() {
        EventEntity event = TestDataUtilities.createTestEventEntityA();
        eventRepository.save(event);
        Optional<EventEntity> result = eventRepository.findById(event.getEventId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(event);
    }

    @Test
    public void testThatMultipleEventsCanBeCreatedAndRecalled() {
        EventEntity eventA = TestDataUtilities.createTestEventEntityA();
        eventRepository.save(eventA);

        EventEntity eventB = TestDataUtilities.createTestEventEntityB();
        eventRepository.save(eventB);

        EventEntity eventC = TestDataUtilities.createTestEventEntityC();
        eventRepository.save(eventC);

        Iterable<EventEntity> result = eventRepository.findAll();
        System.out.println(result);
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        eventA,
                        eventB,
                        eventC
                );
    }

    @Test
    public void testThatEventCanBeUpdated(){
        EventEntity eventA = TestDataUtilities.createTestEventEntityA();
        eventRepository.save(eventA);

        Optional<EventEntity> result = eventRepository.findById(eventA.getEventId());
        assertThat(result).isPresent();
        assertThat(result.get().getEventName()).isEqualTo("testA");

        eventA.setEventName("UPDATED");

        eventRepository.save(eventA);

        result = eventRepository.findById(eventA.getEventId());
        assertThat(result).isPresent();
        assertThat(result.get().getEventName()).isEqualTo("UPDATED");
    }

    @Test
    public void testThatEventCanBeDeleted(){
        EventEntity eventA = TestDataUtilities.createTestEventEntityA();
        eventRepository.save(eventA);

        Optional<EventEntity> result = eventRepository.findById(eventA.getEventId());
        assertThat(result).isPresent();

        eventRepository.delete(eventA);
        result = eventRepository.findById(eventA.getEventId());
        assertThat(result).isEmpty();

    }
}
