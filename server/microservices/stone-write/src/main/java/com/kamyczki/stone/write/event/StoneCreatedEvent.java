package com.kamyczki.stone.write.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@ToString(callSuper = true)
class StoneCreatedEvent extends StoneEvent {
    private String name;
    private String description;
    private String zipCode;
}
