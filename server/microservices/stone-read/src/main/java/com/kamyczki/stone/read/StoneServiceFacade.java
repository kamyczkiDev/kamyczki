package com.kamyczki.stone.read;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;

public interface StoneServiceFacade {

    void addStone(StoneCreatedEventDto stoneCreatedEventDto);
}
