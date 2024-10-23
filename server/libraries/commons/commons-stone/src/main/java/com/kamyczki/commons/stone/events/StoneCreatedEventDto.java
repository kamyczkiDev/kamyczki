package com.kamyczki.commons.stone.events;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class StoneCreatedEventDto extends StoneEventDto {
    protected String name;
    protected String description;
    protected String zipCode;
}
