package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.controller.dto.BatchSavePostsDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostDtoV1;

import java.util.List;

public interface PostService {
    List<PostDtoV1> listAll();

    Boolean batchSave(BatchSavePostsDtoV1 dto);

    PostDtoV1 details(String postId);
}
