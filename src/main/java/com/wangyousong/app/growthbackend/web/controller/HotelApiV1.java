package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.HotelService;
import com.wangyousong.app.growthbackend.web.controller.dto.HotelDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hotels")
public class HotelApiV1 {

    private final HotelService hotelService;

    @CrossOrigin
    @GetMapping
    public R<List<HotelDtoV1>> list() {
        return R.success(hotelService.list());
    }

}
