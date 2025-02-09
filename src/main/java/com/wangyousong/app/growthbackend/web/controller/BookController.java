package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import com.wangyousong.app.growthbackend.web.response.BookStatisticResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(tags = "books api")
@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    @ApiOperation("create a book")
    public R<String> create(@Valid @RequestBody BookRequest dto) {
        return R.success(bookService.create(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("update a book by id")
    public R<Boolean> update(@PathVariable String id, @Valid @RequestBody BookRequest dto) {
        return R.success(bookService.update(id, dto));
    }

    @GetMapping
    @ApiOperation("list all books by page")
    public R<Page<BookResponse>> list(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<BookResponse> data = bookService.list(pageRequest);
        return R.success(data);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete a book by id")
    public R<Boolean> delete(@PathVariable String id) {
        return R.success(bookService.deleteBy(id));
    }

    @PatchMapping("/{id}/hidden")
    @ApiOperation("toggle hidden a book by id")
    public R<Boolean> toggleHidden(@PathVariable String id, @RequestParam boolean hidden) {
        return R.success(bookService.toggleHidden(id, hidden));
    }

    @GetMapping("/statistic")
    @ApiOperation("get book statistic")
    public R<BookStatisticResponse> statistic() {
        return R.success(bookService.statistic());
    }

}
