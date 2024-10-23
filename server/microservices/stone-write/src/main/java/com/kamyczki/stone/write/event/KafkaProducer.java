package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneCreatedEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.kamyczki.commons.stone.StoneKafkaConstants.STONE_CREATED_TOPIC;

@Service
class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, StoneCreatedEventDto> kafkaTemplate;

    public void sendEventCreatedMessage(StoneCreatedEventDto message) {
        kafkaTemplate.send(STONE_CREATED_TOPIC, message);
    }
}
