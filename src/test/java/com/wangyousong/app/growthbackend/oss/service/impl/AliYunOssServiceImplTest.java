package com.wangyousong.app.growthbackend.oss.service.impl;

import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import com.wangyousong.app.growthbackend.tools.PdfToImageUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AliYunOssServiceImplTest {

    public static final String PREFIX = "https://growth-public.oss-cn-shanghai.aliyuncs.com/";
    @Autowired
    private AliYunOssService aliYunOssService;

    @Test
    void listAllFiles() {
        List<String> keys = aliYunOssService.listAllFiles("books/it/");
        String urls = keys.stream()
                .map(it -> PREFIX + it)
                .map(this::wrapQuotation)
                .collect(Collectors.joining(","));
        System.out.println(urls);
        assertThat(keys).isNotEmpty();
    }

    private String wrapQuotation(String it) {
        return "\"" + it + "\"";
    }

    @Test
    void should_upload_file() {
        String pathname = "/Users/bob/Desktop/covers/it/OReilly.Optimizing.Java.2018.4.jpg";
        File latestPdf = new File(pathname);
        System.out.println(latestPdf.getName());
        String key = aliYunOssService.upload(latestPdf, "books/it/");
        System.out.println(key);

        postForCreate(key);

        assertNotNull(key);
    }

    @Test
    void should_convert_then_upload_file() {
        PdfToImageUtil.main(new String[]{});
//        File latestPdf = Arrays.stream(Objects.requireNonNull(new File(COVERS_DIR).listFiles()))
//                .filter(it -> it.getName().contains(".jpg"))
//                .max(Comparator.comparing(File::lastModified))
//                .orElseThrow();
//        System.out.println(latestPdf.getName());
//        String key = aliYunOssService.upload(latestPdf, "books/it/");
//        System.out.println(key);
//
//        postForCreate(key);
//
//        assertNotNull(key);
    }

    private static void postForCreate(String key) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject personJsonObject = new JSONObject();
        try {
            personJsonObject.put("id", null);
            personJsonObject.put("cover", AliYunOssServiceImpl.extractUrl(key));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        HttpEntity<String> request =
                new HttpEntity<>(personJsonObject.toString(), headers);
        restTemplate.postForEntity("https://books.wangyousong.com/api/v1/books", request, Object.class);
    }

    @Test
    void should_delete() {
        String bookId = "649eedf924cc591c4d9276d0";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://books.wangyousong.com/api/v1/books/" + bookId + "?token=Growth123");
    }

    @Test
    void should_execute_clear_cache() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("https://books.wangyousong.com/api/v1/books/actions/delete-cache?token=Growth123");
        assertThat(restTemplate).isNotNull();
    }
}