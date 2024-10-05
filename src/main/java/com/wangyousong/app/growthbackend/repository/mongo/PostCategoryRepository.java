package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.PostCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostCategoryRepository extends MongoRepository<PostCategory, String> {
}
