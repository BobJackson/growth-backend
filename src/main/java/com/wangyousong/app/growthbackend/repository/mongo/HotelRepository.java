package com.wangyousong.app.growthbackend.repository.mongo;

import com.wangyousong.app.growthbackend.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {
}
