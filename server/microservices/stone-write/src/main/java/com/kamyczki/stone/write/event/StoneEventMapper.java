package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.commons.stone.events.StoneEventType;
import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(imports = {StoneEventType.class, LocalDateTime.class, StoneUtils.class})
interface StoneEventMapper {

    StoneEventMapper INSTANCE = Mappers.getMapper(StoneEventMapper.class);

    StoneEventDto toDto(StoneEvent stoneEvent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stoneId", expression = "java(StoneUtils.newStoneId())")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    @Mapping(target = "type", expression = "java(getStoneCreatedType())")
    StoneCreatedEvent toEvent(CreateStoneDto createStoneDto);

    default StoneEventType getStoneCreatedType() {
        return StoneEventType.CREATED;
    }

    //why?
    default StoneCreatedEventDto toStoneCreatedEventDto(StoneCreatedEvent stoneCreatedEvent) {
        if (stoneCreatedEvent == null) {
            return null;
        }

        StoneCreatedEventDto stoneCreatedEventDto = new StoneCreatedEventDto();

        stoneCreatedEventDto.setId(stoneCreatedEvent.getId());
        stoneCreatedEventDto.setTimestamp(stoneCreatedEvent.getTimestamp());
        stoneCreatedEventDto.setStoneId(stoneCreatedEvent.getStoneId());
        stoneCreatedEventDto.setName(stoneCreatedEvent.getName());
        stoneCreatedEventDto.setDescription(stoneCreatedEvent.getDescription());
        stoneCreatedEventDto.setZipCode(stoneCreatedEvent.getZipCode());
        stoneCreatedEventDto.setType(stoneCreatedEvent.getType());

        return stoneCreatedEventDto;
    }
}
