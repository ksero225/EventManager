package com.EventManager.EventManager.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private Long locationId;
    private String address;
    private String locationCity;
    private String locationCountry;
}
