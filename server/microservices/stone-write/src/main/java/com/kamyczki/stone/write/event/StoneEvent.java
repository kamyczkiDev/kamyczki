package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneEventType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "stoneEvents")
@ToString(callSuper = true)
class StoneEvent  {

    @Id protected String id;

    @Indexed
    protected String stoneId;

    protected LocalDateTime timestamp;
    protected StoneEventType type;
}
