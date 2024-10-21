package com.kamyczki.stone.read;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.stone.read.dto.StoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface StoneMapper {

    StoneMapper INSTANCE = Mappers.getMapper(StoneMapper.class);

    StoneDto doStoneDto(Stone stone);

    Stone toStone(StoneCreatedEventDto stoneCreatedEvent);
}
