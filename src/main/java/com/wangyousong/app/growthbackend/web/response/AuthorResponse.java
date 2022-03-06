package com.wangyousong.app.growthbackend.web.response;

import com.wangyousong.app.growthbackend.domain.Author;
import lombok.Getter;

@Getter
public class AuthorResponse {
    private final String id;
    private final String name;

    public AuthorResponse(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}
