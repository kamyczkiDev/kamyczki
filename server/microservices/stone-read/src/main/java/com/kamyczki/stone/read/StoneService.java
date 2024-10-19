package com.kamyczki.stone.read;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.stone.read.dto.StoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.error.ErrorCodes.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StoneService {

    private final StoneRepository stoneRepository;
    private static final StoneMapper STONE_MAPPER = StoneMapper.INSTANCE;

    public void addStone(StoneCreatedEventDto stoneCreatedEvent) {
        stoneRepository.save(STONE_MAPPER.toStone(stoneCreatedEvent));
    }

    StoneDto getById(String stoneId) {
        return stoneRepository.findById(stoneId)
                .map(STONE_MAPPER::doStoneDto)
                .orElseThrow(() -> RESOURCE_NOT_FOUND.throwWithObjectAndFieldAndValue("stone", "id", stoneId));
    }
}
