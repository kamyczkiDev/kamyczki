package com.kamyczki.stone.read;

import com.kamyczki.stone.read.dto.StoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/stone")
@RestController
@RequiredArgsConstructor
public class StoneController {

    private final StoneService stoneService;

    @GetMapping("{id}")
    private StoneDto getStone(@PathVariable String stoneId){
        return stoneService.getById(stoneId);
    }
}
