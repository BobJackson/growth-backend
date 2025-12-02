package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.BookService;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV2;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import com.wangyousong.app.growthbackend.web.response.BookStatisticResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wangyousong.app.growthbackend.common.DefaultSort.DEFAULT_SORT;
import static org.springframework.data.domain.Sort.Direction.ASC;

@Tag(name = "books api")
@RestController
@RequestMapping("/books")
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping
    @Operation(summary = "create a book")
    public R<String> create(@Valid @RequestBody BookRequest dto) {
        return R.success(bookService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update a book by id")
    public R<Boolean> update(@PathVariable String id, @Valid @RequestBody BookRequest dto) {
        return R.success(bookService.update(id, dto));
    }

    @GetMapping
    @Operation(summary = "list all books by page")
    public R<Page<BookResponse>> list(@RequestParam int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size).withSort(DEFAULT_SORT);
        Page<BookResponse> data = bookService.list(pageRequest);
        return R.success(data);
    }

    @GetMapping("/all")
    @CrossOrigin
    @Operation(summary = "list all books")
    public R<List<BookDtoV2>> listAll() {
        PageRequest pageRequest = PageRequest.of(0, 1000).withSort(Sort.by(ASC, "title"));
        List<BookDtoV2> books = bookService.listAll(pageRequest);
        return R.success(books);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a book by id")
    public R<Boolean> delete(@PathVariable String id) {
        return R.success(bookService.deleteBy(id));
    }

    @PatchMapping("/{id}/hidden")
    @Operation(summary = "toggle hidden a book by id")
    public R<Boolean> toggleHidden(@PathVariable String id, @RequestParam boolean hidden) {
        return R.success(bookService.toggleHidden(id, hidden));
    }

    @GetMapping("/statistic")
    @Operation(summary = "get book statistic")
    public R<BookStatisticResponse> statistic() {
        return R.success(bookService.statistic());
    }

    @PatchMapping("/{id}/actions/remove-black-border")
    @Operation(summary = "remove black border")
    public R<Boolean> removeBlackBorder(@PathVariable String id) {
        bookService.removeBlackBorder(id);
        return R.success(true);
    }

}
