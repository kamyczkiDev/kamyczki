package com.kamyczki.stone.write.event.dto;

import com.kamyczki.stone.write.event.type.StoneEventType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StoneEventDto {

    private String id;

    private String stoneId;
    private LocalDateTime timestamp;
    private StoneEventType type;
}
