package com.wangyousong.app.growthbackend.data;

import com.wangyousong.app.growthbackend.domain.Hotel;
import com.wangyousong.app.growthbackend.repository.mongo.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class HotelDataInitializer {

    @Autowired
    private HotelRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void initialize() throws IOException {
        HotelDataCollector collector = new HotelDataCollector();
        List<Hotel> hotels = collector.parse();
        repository.saveAll(hotels);
    }
}
