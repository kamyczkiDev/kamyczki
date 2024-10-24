package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("api/stone")
@RequiredArgsConstructor
class StoneEventController {

    private final StoneEventService stoneEventService;

    @PostMapping("create")
    StoneEventDto createStone(@RequestBody CreateStoneDto stoneCreatedEvent) {
        return stoneEventService.createStone(stoneCreatedEvent);
    }
}