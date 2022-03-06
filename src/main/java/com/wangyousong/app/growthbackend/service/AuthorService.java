package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.Author;
import com.wangyousong.app.growthbackend.web.request.AuthorRequest;
import com.wangyousong.app.growthbackend.web.response.AuthorResponse;

import java.util.Collection;
import java.util.Optional;

public interface AuthorService {
    AuthorResponse findById(String id);

    Optional<Author> findTop1ByName(String name);

    String create(AuthorRequest request);

    Collection<Author> createIfNotExist(Collection<String> authors);
}
