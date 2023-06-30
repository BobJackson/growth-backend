package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.SimpleBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SimpleBookRepository extends MongoRepository<SimpleBook,String> {
    Optional<SimpleBook> findByCoverUrl(String cover);
}
