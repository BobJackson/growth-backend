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
        Finder<PostCategoryDtoV1, String> finder = buildPostCategoryFinder();
        List<Post> posts = dto.getPosts()
                .stream()
                .map(it -> it.toEntity(finder.findEntityBy(it.getCategory()).getId()))
                .toList();
        repository.saveAll(posts);
        return true;
    }

    private Finder<PostCategoryDtoV1, String> buildPostCategoryFinder() {
        List<PostCategoryDtoV1> categories = postCategoryService.listAll();
        return new Finder<>(categories, PostCategoryDtoV1::getName);
    }

    @Override
    public PostDtoV1 details(String postId) {
        Post post = repository.findById(postId).orElseThrow();
        PostCategoryDtoV1 category = postCategoryService.findById(post.getPostCategoryId());
        return PostDtoV1.from(post, category.getName());
    }
}
