package com.EventManager.EventManager.mappers.implementations;

import com.EventManager.EventManager.domain.dto.LocationDto;
import com.EventManager.EventManager.domain.entities.LocationEntity;
import com.EventManager.EventManager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LocationMapperImpl implements Mapper<LocationEntity, LocationDto> {
    private final ModelMapper modelMapper;

    public LocationMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LocationDto mapTo(LocationEntity locationEntity) {
        return modelMapper.map(locationEntity, LocationDto.class);
    }

    @Override
    public LocationEntity mapFrom(LocationDto locationDto) {
        return modelMapper.map(locationDto, LocationEntity.class);
    }
}
