package com.kamyczki.stone.write.event;

import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import com.kamyczki.stone.write.event.dto.StoneEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stone")
@RequiredArgsConstructor
public class StoneEventController {

    private final StoneEventService stoneEventService;

    @PostMapping("create")
    StoneEventDto createStone(@RequestBody CreateStoneDto stoneCreatedEvent){
        return stoneEventService.createStone(stoneCreatedEvent);
    }
}
