package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.domain.SimpleBook;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;

import java.util.List;

public interface SimpleBookService {
    boolean create(BookDtoV1 dto);

    boolean batchCreate(List<String> urls);

    List<SimpleBook> listAll();
}
