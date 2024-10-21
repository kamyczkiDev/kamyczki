package com.kamyczki.stone.read.kafka;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.stone.StoneKafkaConstants.STONE_CREATED_TOPIC;

@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
class StoneEventKafkaConsumer {

    private final EventService eventService;

    @KafkaListener(topics = STONE_CREATED_TOPIC, groupId = "stone-read-group")
    void consume(StoneCreatedEventDto message) {
        eventService.consumeEvent(message);
    }
}
