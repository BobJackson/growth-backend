package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import com.wangyousong.app.growthbackend.web.controller.dto.BookDtoV1;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
public class BookApiV1 {

    @Resource
    private IdService idService;

    @Resource
    private AliYunOssService aliYunOssService;

    @Cacheable(cacheNames = {"bookList"})
    @CrossOrigin
    @GetMapping
    public R<List<BookDtoV1>> list() {
        List<String> keys = aliYunOssService.listAllFiles("books/it/");
        List<BookDtoV1> result = keys.stream()
                .map(it-> new BookDtoV1(idService.generateId(), "https://growth-public.oss-cn-shanghai.aliyuncs.com/" + it))
                .toList();
        return R.success(result);
    }
}
