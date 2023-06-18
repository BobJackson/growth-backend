package com.wangyousong.app.growthbackend.oss.service.impl;

import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import com.wangyousong.app.growthbackend.tools.PdfToImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.wangyousong.app.growthbackend.tools.PdfToImageUtil.COVERS_DIR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    void should_upload_file() {
        PdfToImageUtil.main(new String[]{});
        File latestPdf = Arrays.stream(Objects.requireNonNull(new File(COVERS_DIR).listFiles()))
                .filter(it-> it.getName().contains(".jpg"))
                .max(Comparator.comparing(File::lastModified))
                .orElseThrow();
        System.out.println(latestPdf.getName());
        String key = aliYunOssService.upload(latestPdf, "books/it/");
        System.out.println(key);
        assertNotNull(key);
    }

    @Test
    void should_execute_clear_cache() throws IOException, InterruptedException {
        String command = "curl --location --request DELETE 'https://books.wangyousong.com/api/v1/books/actions/delete-cache?token=Growth123'";
        Process process = Runtime.getRuntime().exec(command);
        boolean wait = process.waitFor(10, TimeUnit.SECONDS);
        assertThat(wait).isTrue();
    }
}