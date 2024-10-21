package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kamyczki.stone.write.event.StoneUtils.newStoneId;

@Service
@RequiredArgsConstructor
class StoneEventService {

    private static final StoneEventMapper STONE_EVENT_MAPPER = StoneEventMapper.INSTANCE;

    private final StoneEventRepository stoneEventRepository;
    private final KafkaProducer kafkaProducer;

    StoneEventDto createStone(CreateStoneDto stoneCreatedEvent) {
        return saveEvent(STONE_EVENT_MAPPER.toEvent(stoneCreatedEvent));
    }

    private <T extends StoneEvent> StoneEventDto saveEvent(T stoneEvent) {
        appendIdToNewStones(stoneEvent);

        var savedEvent = stoneEventRepository.save(stoneEvent);

        sendMessage(stoneEvent);

        return STONE_EVENT_MAPPER.toDto(savedEvent);
    }

    private <T extends StoneEvent> void sendMessage(T stoneEvent) {
        if(stoneEvent instanceof StoneCreatedEvent stoneCreatedEvent) {
            kafkaProducer.sendEventCreatedMessage(STONE_EVENT_MAPPER.toStoneCreatedEventDto(stoneCreatedEvent));
        }
    }

    private <T extends StoneEvent> void appendIdToNewStones(T stoneEvent) {
        if(stoneEvent instanceof StoneCreatedEvent) {
            //TODO: consider generating pool of available ids, maybe every day or sth?
           while (stoneEventRepository.existsByStoneId(stoneEvent.getStoneId())){
               stoneEvent.setStoneId(newStoneId());
           }
        }
    }
}
