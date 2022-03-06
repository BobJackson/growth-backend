package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.Category;
import com.wangyousong.app.growthbackend.web.request.CategoryRequest;
import com.wangyousong.app.growthbackend.web.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse findById(String id);

    List<CategoryResponse> findByName(String name);

    String create(CategoryRequest request);

    Category createIfNotExist(String category);
}
