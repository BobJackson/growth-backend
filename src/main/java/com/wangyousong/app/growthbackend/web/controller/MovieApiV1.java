package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.MovieService;
import com.wangyousong.app.growthbackend.web.controller.dto.MovieDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/movies")
public class MovieApiV1 {

    private final MovieService movieService;

    @CrossOrigin
    @GetMapping
    public R<List<MovieDtoV1>> list() {
        return R.success(movieService.list());
    }
}
