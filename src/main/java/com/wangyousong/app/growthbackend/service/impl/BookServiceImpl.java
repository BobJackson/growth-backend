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
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import com.wangyousong.app.growthbackend.web.response.BookStatisticResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        Book book = createFrom(dto, idService.generateId());

        Book entity = repository.save(book);
        return entity.getId();
    }

    private Book createFrom(BookRequest dto, String id) {
        Book book = dto.toEntity(id);
        book.setAuthors(buildAuthors(dto.getAuthors()));
        book.setCategory(buildCategory(dto.getCategory()));
        book.setTags(buildTags(dto.getTags()));
        return book;
    }

    @Override
    public Boolean update(String id, BookRequest dto) {
        Book book = createFrom(dto, id);
        repository.save(book);
        return true;
    }

    private Collection<Author> buildAuthors(Set<String> authors) {
        return authorService.createIfNotExist(authors);
    }

    private Category buildCategory(String category) {
        return categoryService.createIfNotExist(category);
    }

    private Collection<Tag> buildTags(Set<String> tags) {
        if (CollectionUtils.isEmpty(tags)) {
            return Collections.emptyList();
        }
        return tagService.createIfNotExist(tags);
    }

    @Override
    public Page<BookResponse> list(PageRequest pageRequest) {
        Page<Book> books = repository.findAll(pageRequest);
        return books.map(BookResponse::new);
    }

    @CacheEvict(cacheNames = {"bookList"})
    @Override
    public void clearBookListCache() {
        log.info("will clear book list cache");
    }

    @Override
    public Boolean deleteBy(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Boolean toggleHidden(String id, boolean hidden) {
        repository.findById(id).ifPresent(book -> {
            book.setHidden(hidden);
            repository.save(book);
        });
        return true;
    }

    @Override
    public BookStatisticResponse statistic() {
        BookStatisticResponse response = new BookStatisticResponse();
        response.setTotal(repository.count());
        response.setHidden(repository.countByHidden(true));
        return response;
    }

    @Override
    public Boolean removeBlackBorder(String id) {
        Book book = repository.findById(id).orElseThrow();
        String cover = book.getCover();
        // TODO impl it
        return true;
    }

    @Override
    public List<BookDtoV1> listAll(PageRequest pageRequest) {
        List<Book> books = repository.findAllByHidden(false, pageRequest);
        return books.stream()
                .map(it -> new BookDtoV1(it.getId(), it.getCover()))
                .toList();
    }
}
