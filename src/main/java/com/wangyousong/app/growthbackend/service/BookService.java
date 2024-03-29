package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BookService {
    String create(BookRequest dto);

    Page<BookResponse> list(PageRequest pageRequest);

    void clearBookListCache();
}
