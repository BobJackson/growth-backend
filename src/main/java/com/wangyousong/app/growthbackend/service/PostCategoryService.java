package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.controller.dto.PostCategoryDtoV1;

import java.util.List;

public interface PostCategoryService {

    List<PostCategoryDtoV1> listAll();
}
