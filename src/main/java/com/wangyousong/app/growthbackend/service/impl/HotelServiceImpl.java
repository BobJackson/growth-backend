package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.repository.mongo.HotelRepository;
import com.wangyousong.app.growthbackend.service.HotelService;
import com.wangyousong.app.growthbackend.web.controller.dto.HotelDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public List<HotelDtoV1> list() {
        return hotelRepository.findAll()
                .stream()
                .map(HotelDtoV1::toHotelDtoV1)
                .toList();
    }
}
