package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.SimpleBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimpleBookRepository extends MongoRepository<SimpleBook,String> {
}
