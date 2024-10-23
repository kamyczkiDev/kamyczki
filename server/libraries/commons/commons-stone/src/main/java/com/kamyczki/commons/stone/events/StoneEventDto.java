package com.kamyczki.commons.stone.events;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class StoneEventDto {
    protected String id;
    protected String stoneId;
    protected LocalDateTime timestamp;
    protected StoneEventType type;
}
