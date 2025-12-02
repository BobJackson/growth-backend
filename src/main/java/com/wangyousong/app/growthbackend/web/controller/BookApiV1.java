package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.service.SimpleBookService;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import com.wangyousong.app.growthbackend.web.controller.dto.SimpleBookDtoList;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static com.wangyousong.app.growthbackend.common.R.success;

@RestController
@RequestMapping("/v1/books")
public class BookApiV1 {
    @Resource
    private BookService bookService;
    @Value("${book.cache.clear.token}")
    private String token;
    @Resource
    private SimpleBookService simpleBookService;

    @Cacheable(cacheNames = {"bookList"})
    @CrossOrigin
    @GetMapping
    public R<List<BookDtoV1>> list() {
        List<BookDtoV1> result = simpleBookService.listAll()
                .stream()
                .map(it -> new BookDtoV1(it.getId(), it.getCoverUrl()))
                .sorted(Comparator.comparing(BookDtoV1::cover))
                .toList();
        return success(result);
    }

    @DeleteMapping("/actions/delete-cache")
    public R<Void> clearCache(@RequestParam String token) {
        if (!Objects.equals(token, this.token)) {
            throw new IllegalArgumentException("token is not right!");
        }
        bookService.clearBookListCache();
        return success();
    }

    @PostMapping("/bulk")
    public R<Boolean> batchCreate(@RequestBody SimpleBookDtoList list) {
        return R.success(simpleBookService.batchCreate(list.getUrls()));
    }

    @PostMapping()
    public R<Boolean> create(@RequestBody BookDtoV1 dto) {
        return R.success(simpleBookService.create(dto));
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable String id, @RequestParam String token) {
        if (!Objects.equals(token, this.token)) {
            throw new IllegalArgumentException("token is not right!");
        }
        simpleBookService.deleteById(id);
        return R.success();
    }
}
