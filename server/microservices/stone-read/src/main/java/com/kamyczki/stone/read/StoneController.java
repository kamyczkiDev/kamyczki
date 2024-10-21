package com.kamyczki.stone.read;

import com.kamyczki.stone.read.dto.StoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RequestMapping("api/stone")
@RestController
@RequiredArgsConstructor
class StoneController {

    private final StoneService stoneService;

    @GetMapping("{id}")
    private StoneDto getStone(@PathVariable String stoneId){
        return stoneService.getById(stoneId);
    }

    @GetMapping
    private List<StoneDto> getAll(){
        return stoneService.getAll();
    }
}
