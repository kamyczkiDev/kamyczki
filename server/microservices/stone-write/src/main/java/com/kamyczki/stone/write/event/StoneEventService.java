package com.kamyczki.stone.write.event;

import com.kamyczki.commons.stone.events.StoneEventDto;
import com.kamyczki.stone.write.event.dto.CreateStoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kamyczki.stone.write.event.StoneUtils.newStoneId;

@Service
@RequiredArgsConstructor
public class StoneEventService {

    private final StoneEventRepository stoneEventRepository;
    private final KafkaProducer kafkaProducer;
    private static final StoneEventMapper STONE_EVENT_MAPPER = StoneEventMapper.INSTANCE;

    public StoneEventDto createStone(CreateStoneDto stoneCreatedEvent) {
        return saveEvent(STONE_EVENT_MAPPER.toEvent(stoneCreatedEvent));
    }

    private <T extends StoneEvent> StoneEventDto saveEvent(T stoneEvent) {
        //TODO: consider generating pool of available ids, maybe every day or sth?
        if(stoneEvent instanceof StoneCreatedEvent) {
           while (stoneEventRepository.existsByStoneId(stoneEvent.getStoneId())){
               stoneEvent.setStoneId(newStoneId());
           }
        }
        var savedEvent = stoneEventRepository.save(stoneEvent);
        kafkaProducer.sendMessage(STONE_EVENT_MAPPER.toDto(savedEvent));
        return STONE_EVENT_MAPPER.toDto(savedEvent);
    }
}
