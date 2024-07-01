package com.wangyousong.app.growthbackend;

import com.wangyousong.app.growthbackend.domain.SimpleBook;
import com.wangyousong.app.growthbackend.repository.mongo.SimpleBookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleBookRepositoryTest {

    @Autowired
    private SimpleBookRepository simpleBookRepository;

    @Test
    void should_save_book_only_using_cover_url() {
        SimpleBook simpleBook = new SimpleBook("https://growth-public.oss-cn-shanghai.aliyuncs.com/books/it/Elasticsearch.The.Definitive.Guide.2015.1.jpg");
        simpleBookRepository.save(simpleBook);
    }

    @Test
    void should_save_book_using_id_and_cover_url() {
        SimpleBook simpleBook = new SimpleBook("https://growth-public.oss-cn-shanghai.aliyuncs.com/books/it/Elasticsearch.The.Definitive.Guide.2015.1.jpg");
        simpleBook.setId("649d8c95f46f435d9f2f4523");

        // com.wangyousong.app.growthbackend.domain.SimpleBook

        simpleBookRepository.save(simpleBook);
    }
}
