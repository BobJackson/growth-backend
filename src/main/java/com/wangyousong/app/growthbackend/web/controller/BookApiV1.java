package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.wangyousong.app.growthbackend.common.R.success;

@RestController
@RequestMapping("/v1/books")
public class BookApiV1 {

    @Resource
    private IdService idService;

    @Resource
    private AliYunOssService aliYunOssService;

    @Resource
    private BookService bookService;
    @Value("${book.cache.clear.token}")
    private String token;

    @Cacheable(cacheNames = {"bookList"})
    @CrossOrigin
    @GetMapping
    public R<List<BookDtoV1>> list() {
        List<String> keys = aliYunOssService.listAllFiles("books/it/");
        List<BookDtoV1> result = keys.stream()
                .map(it -> new BookDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/" + it))
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
}
