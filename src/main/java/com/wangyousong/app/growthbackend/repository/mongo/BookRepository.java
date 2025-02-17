package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    Long countByHidden(boolean hidden);

    List<Book> findAllByHidden(boolean hidden, PageRequest pageRequest);
}
