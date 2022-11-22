package com.wangyousong.app.growthbackend.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @author: wangyousong
 * @date: 2019.09.24
 */
public interface AliYunOssService {
    String upload(File file);

    String upload(File file, String prefix);

    String upload(String key, File file);

    List<String> upload(MultipartFile[] files);

    List<String> upload(List<String> keys, MultipartFile[] files);

    String uploadInputStream(String key, InputStream is);

    String upload(MultipartFile file);

    String upload(String key, MultipartFile file);

    List<String> listAllFiles(String keyPrefix);

    String generatePreSignedUrl(String url, boolean hasPrefix);
}
