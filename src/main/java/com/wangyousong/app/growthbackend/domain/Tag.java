package com.wangyousong.app.growthbackend.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "tags")
public class Tag extends BaseDomain {
    private String name;

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
