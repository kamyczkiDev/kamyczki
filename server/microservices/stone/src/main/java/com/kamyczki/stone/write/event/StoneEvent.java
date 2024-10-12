package com.kamyczki.stone.write.event;

import com.kamyczki.stone.write.event.type.StoneEventType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "stoneEvents")
public class StoneEvent {

    @Id private String id;

    @Indexed
    private String stoneId;

    private LocalDateTime timestamp;
    private StoneEventType type;
}
