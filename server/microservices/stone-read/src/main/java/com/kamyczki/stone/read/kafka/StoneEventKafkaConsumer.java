package com.kamyczki.stone.read.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StoneEventKafkaConsumer {
private final EventService eventService;
private final ObjectMapper objectMapper;
    @KafkaListener(topics = "stone_events", groupId = "my-consumer-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        eventService.consumeEvent(objectMapper.convertValue(message, StoneCreatedEventDto.class));
    }
}
