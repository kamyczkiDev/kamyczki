package com.kamyczki.stone.read.kafka;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.stone.read.StoneServiceFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EventService {

    private final StoneServiceFacade stoneServiceFacade;

    void consumeEvent(StoneEventDto stoneEventInterface) {
        switch (stoneEventInterface.getType()) {
            case FOUND -> {
                //stub
            }
            case CREATED -> stoneServiceFacade.addStone((StoneCreatedEventDto) stoneEventInterface);
        }
    }
}
