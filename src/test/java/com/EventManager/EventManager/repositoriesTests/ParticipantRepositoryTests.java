package com.EventManager.EventManager.repositoriesTests;

import com.EventManager.EventManager.TestDataUtilities;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.repositories.ParticipantRepository;
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
public class ParticipantRepositoryTests {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantRepositoryTests(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    @Test
    public void testThatParticipantCanBeCreatedAndRecalled(){
        ParticipantEntity participant = TestDataUtilities.createTestParticipantEntityA();
        participantRepository.save(participant);

        Optional<ParticipantEntity> result = participantRepository.findById(participant.getParticipantId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(participant);
    }

    @Test
    public void testThatMultipleParticipantsCanBeCreatedAndRecalled(){
        ParticipantEntity participantA = TestDataUtilities.createTestParticipantEntityA();
        participantRepository.save(participantA);

        ParticipantEntity participantB = TestDataUtilities.createTestParticipantEntityB();
        participantRepository.save(participantB);

        ParticipantEntity participantC = TestDataUtilities.createTestParticipantEntityC();
        participantRepository.save(participantC);

        Iterable<ParticipantEntity> result = participantRepository.findAll();

        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        participantA,
                        participantB,
                        participantC
                );
    }

    @Test
    public void testThatParticipantCanBeUpdated(){
        ParticipantEntity participant = TestDataUtilities.createTestParticipantEntityA();
        participantRepository.save(participant);

        Optional<ParticipantEntity> result = participantRepository.findById(participant.getParticipantId());

        assertThat(result).isPresent();
        assertThat(result.get().getParticipantName()).isEqualTo("participantNameA");

        participant.setParticipantName("UPDATED");
        participantRepository.save(participant);

        result = participantRepository.findById(participant.getParticipantId());

        assertThat(result).isPresent();
        assertThat(result.get().getParticipantName()).isEqualTo("UPDATED");

    }

    @Test
    public void testThatParticipantCanBeDeleted(){
        ParticipantEntity participant = TestDataUtilities.createTestParticipantEntityA();
        participantRepository.save(participant);

        Optional<ParticipantEntity> result = participantRepository.findById(participant.getParticipantId());

        assertThat(result).isPresent();

        participantRepository.delete(participant);

        result = participantRepository.findById(participant.getParticipantId());

        assertThat(result).isEmpty();
    }
}
