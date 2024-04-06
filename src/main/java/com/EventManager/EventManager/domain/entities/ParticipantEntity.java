package com.EventManager.EventManager.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "participants")
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "participant_id")
    private Long participantId;

    @Column(name = "participant_name")
    private String participantName;

    @Column(name = "participant_surname")
    private String participantSurname;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private EventEntity event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantEntity that = (ParticipantEntity) o;
        return Objects.equals(participantId, that.participantId) && Objects.equals(participantName, that.participantName) && Objects.equals(participantSurname, that.participantSurname) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(participantId, participantName, participantSurname, event);
    }

    @Override
    public String toString() {
        return "ParticipantEntity{" +
                "participantId=" + participantId +
                ", participantName='" + participantName + '\'' +
                ", participantSurname='" + participantSurname + '\'' +
                ", event=" + event +
                '}';
    }
}
