package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.domain.Author;
import com.wangyousong.app.growthbackend.exception.RecordNotFound;
import com.wangyousong.app.growthbackend.repository.mongo.AuthorRepository;
import com.wangyousong.app.growthbackend.service.AuthorService;
import com.wangyousong.app.growthbackend.web.request.AuthorRequest;
import com.wangyousong.app.growthbackend.web.response.AuthorResponse;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Resource
    private IdService idService;

    @Resource
    private AuthorRepository repository;

    @Cacheable(value = "authorById")
    @Override
    public AuthorResponse findById(String id) {
        Author author = repository.findById(id).orElseThrow(() -> new RecordNotFound(id));
        return new AuthorResponse(author);
    }

    @Override
    public Optional<Author> findTop1ByName(String name) {
        return repository.findTop1ByName(name);
    }

    @Override
    public String create(AuthorRequest request) {
        Author entity = save(request);
        return entity.getId();
    }

    private Author save(AuthorRequest request) {
        Author author = new Author(idService.generateId(), request.getName());
        return repository.save(author);
    }

    @Override
    public Collection<Author> createIfNotExist(Collection<String> names) {
        List<Author> authors = repository.findByNameIn(names);
        Set<String> existed = authors.stream().map(Author::getName).collect(Collectors.toSet());
        Collection<String> notExited = CollectionUtils.subtract(names, existed);
        Set<Author> created = notExited.stream()
                .map(name -> new Author(idService.generateId(), name))
                .collect(Collectors.toSet());
        repository.saveAll(created);
        return CollectionUtils.union(authors, created);
    }
}
