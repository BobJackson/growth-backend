package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.domain.PostCategory;
import com.wangyousong.app.growthbackend.repository.mongo.PostCategoryRepository;
import com.wangyousong.app.growthbackend.service.PostCategoryService;
import com.wangyousong.app.growthbackend.web.controller.dto.CreateCategoryDtoV1;
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

    @Override
    public Boolean createCategory(CreateCategoryDtoV1 dto) {
        PostCategory category = new PostCategory();
        category.setName(dto.getName());
        category.setId(dto.getId());
        repository.save(category);
        return true;
    }
}
