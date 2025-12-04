package com.wangyousong.app.growthbackend.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

/**
 * @author: wangyousong
 * @date: 2019.09.24
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
@ImportRuntimeHints(AliYunOssRuntimeHints.class)
public class AliYunOssConfig {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private Boolean mappingEnabled;
    private String mappingDomain;

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(this.getEndpoint(), this.getAccessKeyId(), this.getAccessKeySecret());
    }
}
