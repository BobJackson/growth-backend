package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "books api")
@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookService bookService;

    @ApiOperation("create a book")
    @PostMapping
    public R<String> create(@RequestBody BookRequest dto) {
        return R.success(bookService.create(dto));
    }

    @ApiOperation("list all books by page")
    @GetMapping
    public R<Page<BookResponse>> list(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<BookResponse> data = bookService.list(pageRequest);
        return R.success(data);
    }

}
