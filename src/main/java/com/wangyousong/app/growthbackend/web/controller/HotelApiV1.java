package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.repository.mongo.HotelRepository;
import com.wangyousong.app.growthbackend.web.controller.dto.HotelDtoV1;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/hotels")
public class HotelApiV1 {

    @Resource
    private HotelRepository hotelRepository;

    @Cacheable(cacheNames = {"hotelList"})
    @CrossOrigin
    @GetMapping
    public R<List<HotelDtoV1>> list() {
        List<HotelDtoV1> data = hotelRepository.findAll()
                .stream()
                .map(HotelDtoV1::toHotelDtoV1)
                .toList();
        return R.success(data);
    }

}
