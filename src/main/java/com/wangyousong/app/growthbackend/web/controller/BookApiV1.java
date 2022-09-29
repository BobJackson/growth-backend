package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.common.R;
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

    @Cacheable(cacheNames = {"bookList"})
    @CrossOrigin
    @GetMapping
    public R<List<BookDtoV1>> list() {
        List<BookDtoV1> result = List.of(
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2020/12/1-7.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/08/1-29.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/08/1-18.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/07/1-4.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/07/1-3.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/07/1-2.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/07/1-1.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/06/1-12.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/06/1-10.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-18.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-9.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-8.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-6.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-4.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-3.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-2.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/05/1-1.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-35.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-34.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-19.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-18.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-16.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-14.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/04/1-12.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/03/1-6.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/03/1-1.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/03/1.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/02/1-10.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/02/1-9.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/02/1-8.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2020/06/1-1.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/01/1-8.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/01/1-7.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/01/1-6.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/01/1-4.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2021/12/1-13.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2021/12/1-4.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2021/12/1-3.jpg"),
                new BookDtoV1(idService.generateId(), "https://salttiger.com/wp-content/uploads/2022/08/1-14.jpg")
        );
        return R.success(result);
    }
}
