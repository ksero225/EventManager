package com.EventManager.EventManager.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;
    @Column(name = "location_address")
    private String address;
    @Column(name = "location_city")
    private String locationCity;
    @Column(name = "location_country")
    private String locationCountry;
}
