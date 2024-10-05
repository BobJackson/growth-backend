package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.PostCategoryService;
import com.wangyousong.app.growthbackend.service.PostService;
import com.wangyousong.app.growthbackend.web.controller.dto.BatchSavePostsDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.CreateCategoryDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostCategoryDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.PostDtoV1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/website")
@RequiredArgsConstructor

public class WebsiteApiV1 {

    private final PostCategoryService categoryService;
    private final PostService postService;

    @GetMapping("/categories")
    @CrossOrigin
    public R<List<PostCategoryDtoV1>> listCategories() {
        return R.success(categoryService.listAll());
    }

    @PostMapping("/categories")
    public R<Boolean> createCategory(@RequestBody CreateCategoryDtoV1 dto) {
        return R.success(categoryService.createCategory(dto));
    }

    @GetMapping("/posts")
    @CrossOrigin
    public R<List<PostDtoV1>> listAllPosts() {
        return R.success(postService.listAll());
    }

    @PostMapping("/posts/bulk")
    public R<Boolean> batchSave(@RequestBody BatchSavePostsDtoV1 dto) {
        return R.success(postService.batchSave(dto));
    }
}
