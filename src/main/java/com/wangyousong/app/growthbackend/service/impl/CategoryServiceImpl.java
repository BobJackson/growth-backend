package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.domain.Category;
import com.wangyousong.app.growthbackend.exception.RecordNotFound;
import com.wangyousong.app.growthbackend.repository.mongo.CategoryRepository;
import com.wangyousong.app.growthbackend.service.CategoryService;
import com.wangyousong.app.growthbackend.web.request.CategoryRequest;
import com.wangyousong.app.growthbackend.web.response.CategoryResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository repository;
    @Resource
    private IdService idService;

    @Cacheable(value = "categoryById")
    @Override
    public CategoryResponse findById(String id) {
        Category category = repository.findById(id).orElseThrow(() -> new RecordNotFound(id));
        return new CategoryResponse(category);
    }

    @Cacheable(value = "categoryByName")
    @Override
    public List<CategoryResponse> findByName(String name) {
        List<Category> categories = repository.findByName(name);
        return categories.stream().map(CategoryResponse::new).toList();
    }

    @Override
    public String create(CategoryRequest request) {
        Category entity = save(request);
        return entity.getId();
    }

    private Category save(CategoryRequest request) {
        Category category = new Category(idService.generateId(), request.getName());
        return repository.save(category);
    }

    @Override
    public Category createIfNotExist(String name) {
        Optional<Category> optional = repository.findTop1ByName(name);
        return optional.orElseGet(() -> save(new CategoryRequest(name)));
    }
}
