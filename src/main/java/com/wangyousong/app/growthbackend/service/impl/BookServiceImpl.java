package com.wangyousong.app.growthbackend.service.impl;

import cn.hutool.core.io.IoUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.domain.Author;
import com.wangyousong.app.growthbackend.domain.Book;
import com.wangyousong.app.growthbackend.domain.Category;
import com.wangyousong.app.growthbackend.domain.Tag;
import com.wangyousong.app.growthbackend.repository.mongo.BookRepository;
import com.wangyousong.app.growthbackend.service.*;
import com.wangyousong.app.growthbackend.tools.ImageUtil;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV2;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import com.wangyousong.app.growthbackend.web.response.BookStatisticResponse;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
    @Resource
    private StsTokenService stsTokenService;

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
    @SneakyThrows
    public void removeBlackBorder(String id) {
        Book book = repository.findById(id).orElseThrow();

        InputStream is = new URL(book.getCover()).openStream();
        OutputStream os = ImageUtil.removeBlackBorder(is);
        InputStream newIs = convertToInputStream(os);

        AssumeRoleResponse.Credentials credentials = stsTokenService.getStsToken();
        OSS ossClient = new OSSClientBuilder().build("https://oss-cn-shanghai.aliyuncs.com", credentials.getAccessKeyId(), credentials.getAccessKeySecret(), credentials.getSecurityToken());
        PutObjectRequest putObjectRequest = new PutObjectRequest("growth-public", id + book.extractFileExtension(), newIs);
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        log.info("upload result {}", result);

        IoUtil.close(newIs);
        IoUtil.close(os);
        IoUtil.close(is);

        book.setCover("https://growth-public.oss-cn-shanghai.aliyuncs.com/" + id + book.extractFileExtension());
        repository.save(book);
    }

    private InputStream convertToInputStream(OutputStream os) {
        if (os instanceof ByteArrayOutputStream baos) {
            return new ByteArrayInputStream(baos.toByteArray());
        }
        throw new IllegalArgumentException("Unsupported OutputStream type");
    }

    @Override
    public List<BookDtoV2> listAll(PageRequest pageRequest) {
        List<Book> books = repository.findAllByHidden(false, pageRequest);
        return books.stream()
                .map(it -> new BookDtoV2(it.getId(), it.getCover(), it.getPublisher()))
                .toList();
    }
}
