package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@Document(collection = "post_categories")
public class PostCategory extends BaseDomain {
    private String name;
}
