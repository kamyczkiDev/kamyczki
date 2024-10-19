package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.stone.StoneKafkaConstants.STONE_EVENTS_TOPIC;


@Service
public class KafkaProducer {

// Kafka topic name

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(StoneEventDto message) {
        System.out.println("Sending message: " + message);
        kafkaTemplate.send(STONE_EVENTS_TOPIC, String.valueOf(message));
    }
}
