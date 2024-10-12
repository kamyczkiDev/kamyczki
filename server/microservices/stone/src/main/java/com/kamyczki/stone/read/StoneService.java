package com.kamyczki.stone.read;

import com.kamyczki.stone.read.dto.StoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.error.ErrorCodes.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
 class StoneService {

    private final StoneRepository stoneRepository;
    private static final StoneMapper STONE_MAPPER = StoneMapper.INSTANCE;

    StoneDto getById(String stoneId) {
        return stoneRepository.findById(stoneId)
                .map(STONE_MAPPER::doStoneDto)
                .orElseThrow(()->RESOURCE_NOT_FOUND.throwWithObjectAndFieldAndValue("stone", "id", stoneId));
    }
}
