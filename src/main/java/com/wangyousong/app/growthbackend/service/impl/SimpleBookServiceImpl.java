package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.domain.SimpleBook;
import com.wangyousong.app.growthbackend.repository.mongo.SimpleBookRepository;
import com.wangyousong.app.growthbackend.service.SimpleBookService;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimpleBookServiceImpl implements SimpleBookService {
    @Resource
    private SimpleBookRepository repository;

    @Override
    public boolean create(BookDtoV1 dto) {
        Optional<SimpleBook> optional = repository.findByCoverUrl(dto.cover());
        if (optional.isPresent()) return false;
        repository.save(new SimpleBook(dto.cover()));
        return true;
    }

    @Override
    public boolean batchCreate(List<String> urls) {
        repository.saveAll(urls.stream().map(SimpleBook::new).toList());
        return true;
    }

    @Override
    public List<SimpleBook> listAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
