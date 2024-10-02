package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.TrelloList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrelloListRepository extends MongoRepository<TrelloList, String> {
}
