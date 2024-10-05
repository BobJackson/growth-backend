package com.wangyousong.app.growthbackend.web.controller.dto;

import com.wangyousong.app.growthbackend.domain.PostCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCategoryDtoV1 {
    private String id;
    private String name;

    public PostCategoryDtoV1(PostCategory category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
