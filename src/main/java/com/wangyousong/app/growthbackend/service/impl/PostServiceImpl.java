package com.wangyousong.app.growthbackend.service.impl;


import com.wangyousong.app.growthbackend.common.Finder;
import com.wangyousong.app.growthbackend.domain.Post;
import com.wangyousong.app.growthbackend.repository.mongo.PostRepository;
import com.wangyousong.app.growthbackend.service.PostCategoryService;
import com.wangyousong.app.growthbackend.service.PostService;
import com.wangyousong.app.growthbackend.web.controller.dto.BatchSavePostsDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostCategoryDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final PostCategoryService postCategoryService;

    @Override
    public List<PostDtoV1> listAll() {
        List<PostCategoryDtoV1> categories = postCategoryService.listAll();
        Finder<PostCategoryDtoV1, String> finder = new Finder<>(categories, PostCategoryDtoV1::getId);
        return repository.findAll()
                .stream()
                .map(it -> PostDtoV1.from(it, finder.findBy(it.getPostCategoryId())))
                .toList();
    }

    @Override
    public Boolean batchSave(BatchSavePostsDtoV1 dto) {
        List<PostCategoryDtoV1> categories = postCategoryService.listAll();
        Finder<PostCategoryDtoV1, String> finder = new Finder<>(categories, PostCategoryDtoV1::getName);
        List<Post> posts = dto.getPosts()
                .stream()
                .map(it -> it.toEntity(finder.findEntityBy(it.getCategory()).getId()))
                .toList();
        repository.saveAll(posts);
        return true;
    }
}
