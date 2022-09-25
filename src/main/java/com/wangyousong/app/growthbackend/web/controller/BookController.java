package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    public R<String> create(@RequestBody BookRequest dto) {
        return R.success(bookService.create(dto));
    }

    @GetMapping
    public R<Page<BookResponse>> list(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BookResponse> data = bookService.list(pageRequest);
        return R.success(data);
    }

}
