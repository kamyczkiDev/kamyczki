package com.kamyczki.commons.stone.events;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString(callSuper = true)
public class StoneCreatedEventDto extends StoneEventDto{
    private String name;
    private String description;
    private String zipCode;
}
