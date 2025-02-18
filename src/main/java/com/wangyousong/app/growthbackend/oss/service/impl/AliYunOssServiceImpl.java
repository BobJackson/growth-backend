package com.wangyousong.app.growthbackend.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.wangyousong.app.growthbackend.oss.config.AliYunOssConfig;
import com.wangyousong.app.growthbackend.oss.service.AliYunOssService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.stream.IntStream;


/**
 * @author: wangyousong
 * @date: 2019.09.24
 */
@Service
public class AliYunOssServiceImpl implements AliYunOssService {


    private static final Long TEN_YEAR = 1944574191000L;


    @Resource
    private AliYunOssConfig config;

    @Resource
    private OSS oss;

    @Value("${filePrefix:#{null}}")
    private String filePrefix;

    public static String extractUrl(String rawUrl) {
        return rawUrl.substring(0, rawUrl.indexOf("?Expires="))
                .replace("http:", "https:")
                .replace("growth-public.oss-cn-shanghai.aliyuncs.com/", "growth-public.oss-cn-shanghai.aliyuncs.com/books/it/");
    }

    @Override
    public List<String> listAllFiles(String keyPrefix) {
        ObjectListing objects = oss.listObjects(config.getBucketName(), keyPrefix);
        List<OSSObjectSummary> summaries = objects.getObjectSummaries();
        return summaries
                .stream()
                .map(OSSObjectSummary::getKey)
                .filter(it -> !Objects.equals(it, keyPrefix))
                .toList();
    }

    @Override
    public String generatePreSignedUrl(String url, boolean hasPrefix) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return hasPrefix
                ? oss.generatePresignedUrl(config.getBucketName(), extractKeyFrom(url), new Date(TEN_YEAR)).toString()
                : url;
    }

    private String extractKeyFrom(String url) {
        return filePrefix + url.substring(url.lastIndexOf("/"));
    }

    @Override
    public String upload(File file) {
        return upload(file.getName(), file);
    }

    @Override
    public String upload(File file, String prefix) {
        oss.putObject(config.getBucketName(), prefix + file.getName(), file);
        return generateUrl(file.getName());
    }

    @Override
    public String upload(String key, File file) {
        oss.putObject(config.getBucketName(), key, file);
        return generateUrl(key);
    }

    public String uploadInputStream(InputStream is) {
        return uploadInputStream(UUID.randomUUID().toString(), is);
    }

    @Override
    public String uploadInputStream(String key, InputStream is) {
        oss.putObject(config.getBucketName(), key, is);
        return generateUrl(key);
    }


    public List<String> uploadInputStreams(List<InputStream> inputStreams) {
        if (CollectionUtils.isEmpty(inputStreams)) {
            return Collections.emptyList();
        }
        return inputStreams.stream()
                .map(inputStream -> uploadInputStream(UUID.randomUUID().toString(), inputStream))
                .toList();
    }

    @Override
    public String upload(MultipartFile file) {
        Assert.notNull(file, "文件不能为null!");
        try {
            return uploadInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败! 原因:获取文件流失败！";
    }

    @Override
    public String upload(String key, MultipartFile file) {
        try {
            return uploadInputStream(key, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败! 原因:获取文件流失败！";
    }

    @Override
    public List<String> upload(MultipartFile[] files) {
        Assert.notNull(files, "文件不能为null!");
        if (files.length == 0) {
            return Collections.emptyList();
        }
        List<InputStream> inputStreams = extractInputStream(files);
        return uploadInputStreams(inputStreams);
    }

    private List<InputStream> extractInputStream(MultipartFile[] files) {
        List<InputStream> inputStreams = new ArrayList<>();
        try {
            for (MultipartFile multipartFile : files) {
                inputStreams.add(multipartFile.getInputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStreams;
    }

    @Override
    public List<String> upload(List<String> keys, MultipartFile[] files) {
        if (files.length == 0) {
            return Collections.emptyList();
        }
        List<InputStream> inputStreams = extractInputStream(files);
        return IntStream.range(0, inputStreams.size())
                .mapToObj(i -> uploadInputStream(keys.get(i), inputStreams.get(i)))
                .toList();
    }


    public List<String> upload(List<File> files) {
        if (CollectionUtils.isEmpty(files)) {
            return Collections.emptyList();
        }
        return files.stream()
                .map(t -> upload(UUID.randomUUID().toString(), t))
                .toList();
    }


    public List<String> upload(List<String> keys, List<File> files) {
        return IntStream.range(0, keys.size())
                .mapToObj(i -> upload(keys.get(i), files.get(i)))
                .toList();
    }


    private String generateUrl(String key) {
        Date expiration = new Date(System.currentTimeMillis() + TEN_YEAR);
        URL url = oss.generatePresignedUrl(config.getBucketName(), key, expiration);
        if (Boolean.FALSE.equals(config.getMappingEnabled())) {
            return url.toString();
        }
        URI mappingURI;
        try {
            URI uri = url.toURI();
            if (Boolean.TRUE.equals(config.getMappingEnabled())) {
                HttpHost httpHost = new HttpHost(config.getMappingDomain());
                mappingURI = URIUtils.rewriteURI(uri, httpHost);
            } else {
                mappingURI = uri;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
        return mappingURI.toString();
    }


}
