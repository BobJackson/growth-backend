package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.repository.mongo.PostCategoryRepository;
import com.wangyousong.app.growthbackend.service.PostCategoryService;
import com.wangyousong.app.growthbackend.web.controller.dto.PostCategoryDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService {

    private final PostCategoryRepository repository;

    @Override
    public List<PostCategoryDtoV1> listAll() {
        return repository.findAll()
                .stream()
                .map(PostCategoryDtoV1::new)
                .toList();
    }
}
