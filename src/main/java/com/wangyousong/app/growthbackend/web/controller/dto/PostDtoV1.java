package com.wangyousong.app.growthbackend.web.controller.dto;

import cn.hutool.core.bean.BeanUtil;
import com.wangyousong.app.growthbackend.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
