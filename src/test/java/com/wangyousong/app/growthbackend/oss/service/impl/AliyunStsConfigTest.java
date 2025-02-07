package com.wangyousong.app.growthbackend.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class AliyunStsConfigTest {

    @Test
    void should_upload() {
        String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
        // 从环境变量中获取步骤5生成的临时访问密钥AccessKey ID和AccessKey Secret，非阿里云账号AccessKey ID和AccessKey Secret。
        String accessKeyId = "STS.NV8kp6RnNJbUf8vw2o9pGRMtg";
        String accessKeySecret = "8ud34zJhTaGfpGPvs7Ruc2GZ8gULoAZzRcmPm1JXnDrc";
        // 从环境变量中获取步骤5生成的安全令牌SecurityToken。
        String securityToken = "CAISwgJ1q6Ft5B2yfSjIr5WNIMqCv7Fv/aC+ZB7HkzI6Nf9rvYjfhTz2IHhMe3VrBeAev/UylGxX6vgblqVoRoReREvCKM1565kPHI9WgCyb6aKP9rUhpMCPOwr6UmzWvqL7Z+H+U6muGJOEYEzFkSle2KbzcS7YMXWuLZyOj+wMDL1VJH7aCwBLH9BLPABvhdYHPH/KT5aXPwXtn3DbATgD2GM+qxsmtP7km5XGs0uB1AyjlLRLnemrfMj4NfsLFYxkTtK40NZxcqf8yyNK43BIjvwu1PMYomuX5oDMXgkNvEnYavC26NRqKxRieqUhB76YXjh/cD3K0Aa6tfMUq34lVYk9O0xURIX3etpiOAP6Z/HmJf2ifCGGi+ut3HLUk6OEydKvlKngy3CbmtXIvaac6gEzA05KG3kqy+7SI4C5qvNoufIdGoABSKS9IA8zWeklQvZYrY14W9h3h3Cj6J/kXIpb5A+aaIJWYKbPQxfn6MPgZ1xBb47r9L43Z7yWh3iHMu55O5wgammFFz6hxaY37wZNwiEAzJa6jMZl5BM2LiI34HZA7u7kKc1KdiyuSqWJS7LyCh/DsW7nZ08vdc9JuY5ckkEok0YgAA==";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);
        // 将本地文件exampletest.txt上传至examplebucket。
        PutObjectRequest putObjectRequest = new PutObjectRequest("growth-public", "Clean.Architecture.2017.9.jpg", new File("/Users/bob/Downloads/Clean.Architecture.2017.9.jpg"));

        try {
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            assertThat(result).isNotNull();
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
