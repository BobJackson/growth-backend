package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.controller.dto.PostDtoV1;

import java.util.List;

public interface PostService {
    List<PostDtoV1> listAll();
}
