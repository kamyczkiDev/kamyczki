package com.kamyczki.stone.write.event;

import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import com.kamyczki.stone.write.event.dto.StoneEventDto;
import com.kamyczki.stone.write.event.type.StoneEventType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(imports = {StoneEventType.class, LocalDateTime.class, StoneUtils.class})
public interface StoneEventMapper {

    StoneEventMapper INSTANCE = Mappers.getMapper(StoneEventMapper.class);

    StoneEventDto toDto(StoneEvent stoneEvent);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stoneId", expression = "java(StoneUtils.newStoneId())")
    @Mapping(target = "timestamp", expression = "java(LocalDateTime.now())")
    @Mapping(target = "type", expression = "java(getStoneCreatedType())")
    StoneCreatedEvent toEvent(CreateStoneDto createStoneDto);

    default StoneEventType getStoneCreatedType(){
        return StoneEventType.CREATED;
    }
}
