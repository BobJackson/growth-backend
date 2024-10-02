package com.wangyousong.app.growthbackend.service.impl;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.domain.Author;
import com.wangyousong.app.growthbackend.domain.Book;
import com.wangyousong.app.growthbackend.domain.Category;
import com.wangyousong.app.growthbackend.domain.Tag;
import com.wangyousong.app.growthbackend.repository.mongo.BookRepository;
import com.wangyousong.app.growthbackend.service.AuthorService;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.service.CategoryService;
import com.wangyousong.app.growthbackend.service.TagService;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

import static com.wangyousong.app.growthbackend.common.DefaultSort.DEFAULT_SORT;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private IdService idService;
    @Resource
    private BookRepository repository;
    @Resource
    private AuthorService authorService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;

    @Override
    public String create(BookRequest dto) {
        Book book = dto.toEntity(idService.generateId());
        book.setAuthors(buildAuthors(dto));
        book.setCategory(buildCategory(dto));
        book.setTags(buildTags(dto));

        Book entity = repository.save(book);
        return entity.getId();
    }

    private Collection<Author> buildAuthors(BookRequest dto) {
        return authorService.createIfNotExist(dto.getAuthors());
    }

    private Category buildCategory(BookRequest dto) {
        return categoryService.createIfNotExist(dto.getCategory());
    }

    private Collection<Tag> buildTags(BookRequest dto) {
        if (CollectionUtils.isEmpty(dto.getTags())) {
            return Collections.emptyList();
        }
        return tagService.createIfNotExist(dto.getTags());
    }

    @Override
    public Page<BookResponse> list(PageRequest pageRequest) {
        pageRequest.withSort(DEFAULT_SORT);
        Page<Book> books = repository.findAll(pageRequest);
        return books.map(BookResponse::new);
    }

    @CacheEvict(cacheNames = {"bookList"})
    @Override
    public void clearBookListCache() {
        log.info("will clear book list cache");
    }
}
