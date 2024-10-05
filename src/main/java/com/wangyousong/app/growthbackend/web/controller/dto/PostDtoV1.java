package com.wangyousong.app.growthbackend.web.controller.dto;

import cn.hutool.core.bean.BeanUtil;
import com.wangyousong.app.growthbackend.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class PostDtoV1 {
    private String id;
    private String category;
    private String date;
    private String title;
    private String lead;
    private String content;
    private String image;
    private String source;

    public static PostDtoV1 from(Post post, String category) {
        PostDtoV1 dto = new PostDtoV1();
        BeanUtil.copyProperties(post, dto);
        dto.setCategory(category);
        dto.setDate(post.formattedCreateDate());
        return dto;
    }

    public Post toEntity(String categoryId) {
        Post post = new Post();
        BeanUtil.copyProperties(this, post);
        post.setPostCategoryId(categoryId);
        post.setCreatedAt(LocalDateTime.of(LocalDate.parse(date), LocalTime.now()));
        return post;
    }
}
