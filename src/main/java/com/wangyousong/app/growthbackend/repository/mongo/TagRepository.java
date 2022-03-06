package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findByNameIn(Collection<String> name);

    List<Tag> findByName(String name);
}
