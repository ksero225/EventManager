package com.EventManager.EventManager.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Event")
public class EventEntity {
    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_seq")
    private Long eventId;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_start_date")
    private LocalDateTime eventStartDate;

    @Column(name = "event_end_date")
    private LocalDateTime eventEndDate;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "event_location",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private LocationEntity eventLocalization;

    @Column(name = "event_ticket_price")
    private BigDecimal eventTicketPrice;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ParticipantEntity> participants = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventEntity that = (EventEntity) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventName, that.eventName) && Objects.equals(eventStartDate, that.eventStartDate) && Objects.equals(eventEndDate, that.eventEndDate) && Objects.equals(eventLocalization, that.eventLocalization) && Objects.equals(eventTicketPrice, that.eventTicketPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventStartDate, eventEndDate, eventLocalization, eventTicketPrice);
    }

    @Override
    public String toString() {
        return "EventEntity{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventStartDate=" + eventStartDate +
                ", eventEndDate=" + eventEndDate +
                ", eventLocalization=" + eventLocalization +
                ", eventTicketPrice=" + eventTicketPrice +
                '}';
    }
}
