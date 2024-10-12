package com.kamyczki.stone.read;

import com.kamyczki.stone.read.dto.StoneDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StoneMapper {

    StoneMapper INSTANCE = Mappers.getMapper(StoneMapper.class);

    StoneDto doStoneDto(Stone stone);
}
