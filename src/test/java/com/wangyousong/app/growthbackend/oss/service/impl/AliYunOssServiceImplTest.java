package com.wangyousong.app.growthbackend.oss.service.impl;

import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AliYunOssServiceImplTest {

    @Autowired
    private AliYunOssService aliYunOssService;

    @Test
    void listAllFiles() {
        List<String> keys = aliYunOssService.listAllFiles("books/it/");
        keys.forEach(System.out::println);
        assertThat(keys).isNotEmpty();
    }
}