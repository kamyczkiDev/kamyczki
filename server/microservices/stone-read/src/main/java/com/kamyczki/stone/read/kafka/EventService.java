package com.kamyczki.stone.read.kafka;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.stone.read.StoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final StoneService stoneService;

    void consumeEvent(StoneEventDto stoneEventInterface) {
        switch (stoneEventInterface.getType()) {
            case FOUND -> {
            }
            case CREATED -> stoneService.addStone((StoneCreatedEventDto) stoneEventInterface);
        }
    }
}
