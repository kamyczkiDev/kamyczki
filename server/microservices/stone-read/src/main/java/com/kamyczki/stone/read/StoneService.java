package com.kamyczki.stone.read;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.stone.read.dto.StoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kamyczki.commons.error.ErrorCodes.RESOURCE_NOT_FOUND;

@Service
@RequiredArgsConstructor
class StoneService implements StoneServiceFacade {

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

    List<StoneDto> getAll() {
        return stoneRepository.findAll()
                .stream().map(STONE_MAPPER::doStoneDto)
                .toList();
    }
}
