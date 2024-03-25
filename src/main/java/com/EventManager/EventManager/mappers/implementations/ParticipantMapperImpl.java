package com.EventManager.EventManager.mappers.implementations;

import com.EventManager.EventManager.domain.dto.ParticipantDto;
import com.EventManager.EventManager.domain.entities.ParticipantEntity;
import com.EventManager.EventManager.mappers.Mapper;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ParticipantMapperImpl implements Mapper<ParticipantEntity, ParticipantDto> {
    private final ModelMapper modelMapper;

    public ParticipantMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ParticipantDto mapTo(ParticipantEntity participantEntity) {
        return modelMapper.map(participantEntity, ParticipantDto.class);
    }

    @Override
    public ParticipantEntity mapFrom(ParticipantDto participantDto) {
        return modelMapper.map(participantDto, ParticipantEntity.class);
    }
}
