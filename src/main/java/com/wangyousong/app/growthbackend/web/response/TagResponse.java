package com.wangyousong.app.growthbackend.web.response;

import com.wangyousong.app.growthbackend.domain.Tag;
import lombok.Getter;

@Getter
public class TagResponse {
    private final String id;
    private final String name;

    public TagResponse(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
