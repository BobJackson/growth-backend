package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.Tag;
import com.wangyousong.app.growthbackend.web.request.TagRequest;
import com.wangyousong.app.growthbackend.web.response.TagResponse;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TagService {
    String create(TagRequest request);

    TagResponse findById(String id);

    List<TagResponse> findByName(String name);

    List<TagResponse> list(Collection<String> tagIds);

    Collection<Tag> createIfNotExist(Set<String> tags);
}
