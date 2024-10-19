package com.kamyczki.stone.write.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoneEventRepository extends MongoRepository<StoneEvent,String> {

    boolean existsByStoneId(String stoneId);
}
