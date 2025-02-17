package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import com.wangyousong.app.growthbackend.web.request.BookRequest;
import com.wangyousong.app.growthbackend.web.response.BookResponse;
import com.wangyousong.app.growthbackend.web.response.BookStatisticResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface BookService {
    String create(BookRequest dto);

    Page<BookResponse> list(PageRequest pageRequest);

    void clearBookListCache();

    Boolean deleteBy(String id);

    Boolean toggleHidden(String id, boolean hidden);

    Boolean update(String id, BookRequest dto);

    BookStatisticResponse statistic();

    List<BookDtoV1> listAll(PageRequest pageRequest);
}
