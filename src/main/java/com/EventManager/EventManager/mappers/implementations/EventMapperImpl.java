package com.EventManager.EventManager.mappers.implementations;

import com.EventManager.EventManager.domain.dto.EventDto;
import com.EventManager.EventManager.domain.entities.EventEntity;
import com.EventManager.EventManager.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements Mapper<EventEntity, EventDto> {

    private final ModelMapper modelMapper;

    public EventMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public EventDto mapTo(EventEntity eventEntity) {
        return modelMapper.map(eventEntity, EventDto.class);
    }

    @Override
    public EventEntity mapFrom(EventDto eventDto) {
        return modelMapper.map(eventDto, EventEntity.class);
    }
}
