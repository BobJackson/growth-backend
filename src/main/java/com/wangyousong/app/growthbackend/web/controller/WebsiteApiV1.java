package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.PostCategoryService;
import com.wangyousong.app.growthbackend.service.PostService;
import com.wangyousong.app.growthbackend.web.controller.dto.PostCategoryDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/website")
@RequiredArgsConstructor
@CrossOrigin
public class WebsiteApiV1 {

    private final PostCategoryService categoryService;
    private final PostService postService;

    @GetMapping("/categories")
    public R<List<PostCategoryDtoV1>> listCategories() {
        return R.success(categoryService.listAll());
    }

    @GetMapping("/posts")
    public R<List<PostDtoV1>> listAllPosts() {
        return R.success(postService.listAll());
    }
}
