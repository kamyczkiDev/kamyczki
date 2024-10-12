package com.kamyczki.stone.write.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "stoneEvents")
public class StoneCreatedEvent extends StoneEvent{
    private String name;
    private String description;
    private String zipCode;
}
