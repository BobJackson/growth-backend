package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "posts")
public class Post extends BaseDomain {
    private String postCategoryId;
    private String title;
    private String lead;
    private String content;
    private String image;
    private String source;
}
