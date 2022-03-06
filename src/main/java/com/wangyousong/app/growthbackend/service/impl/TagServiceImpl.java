package com.wangyousong.app.growthbackend.service.impl;

import com.google.common.collect.ImmutableList;
import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.domain.Tag;
import com.wangyousong.app.growthbackend.exception.RecordNotFound;
import com.wangyousong.app.growthbackend.repository.mongo.TagRepository;
import com.wangyousong.app.growthbackend.service.TagService;
import com.wangyousong.app.growthbackend.web.request.TagRequest;
import com.wangyousong.app.growthbackend.web.response.TagResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagRepository repository;
    @Resource
    private IdService idService;

    @Cacheable(value = "tagById")
    @Override
    public TagResponse findById(String id) {
        Tag tag = repository.findById(id).orElseThrow(() -> new RecordNotFound(id));
        return new TagResponse(tag);
    }

    @Cacheable(value = "tagsByName")
    @Override
    public List<TagResponse> findByName(String name) {
        List<Tag> tags = repository.findByName(name);
        return tags.stream().map(TagResponse::new).collect(Collectors.toList());
    }

    @Override
    public String create(TagRequest request) {
        Tag tag = new Tag(idService.generateId(), request.getName());
        Tag entity = repository.save(tag);
        return entity.getId();
    }

    @Override
    public Collection<Tag> createIfNotExist(Set<String> names) {
        List<Tag> tags = repository.findByNameIn(names);
        Set<String> existedTags = tags.stream().map(Tag::getName).collect(Collectors.toSet());
        Collection<String> notExistTags = CollectionUtils.subtract(names, existedTags);
        Set<Tag> createdTags = notExistTags.stream()
                .map(name -> new Tag(idService.generateId(), name))
                .collect(Collectors.toSet());
        repository.saveAll(createdTags);
        return CollectionUtils.union(tags, createdTags);
    }

    @Cacheable(value = "tagsByIds")
    @Override
    public List<TagResponse> list(Collection<String> tagIds) {
        Iterable<Tag> tags = repository.findAllById(tagIds);
        return ImmutableList.copyOf(tags)
                .stream()
                .map(TagResponse::new)
                .collect(Collectors.toList());
    }
}
