package com.wangyousong.app.growthbackend.web.response;

import com.wangyousong.app.growthbackend.domain.Category;
import lombok.Getter;

@Getter
public class CategoryResponse {
    private final String id;
    private final String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
