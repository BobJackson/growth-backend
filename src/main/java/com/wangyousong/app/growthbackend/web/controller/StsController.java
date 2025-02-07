// src/main/java/com/example/demo/controller/StsController.java
package com.wangyousong.app.growthbackend.web.controller;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.wangyousong.app.growthbackend.config.aliyun.AliyunStsConfig;
import com.wangyousong.app.growthbackend.exception.StsTokenGenerateFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

// Security Token Service(STS)
@RestController
@RequestMapping("/sts")
@Slf4j
public class StsController {

    @Resource
    private AliyunStsConfig stsConfig;
    @Resource
    private IAcsClient acsClient;

    @GetMapping("/token")
    public AssumeRoleResponse.Credentials getStsToken() {
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setSysMethod(com.aliyuncs.http.MethodType.POST);
        request.setRoleArn(stsConfig.getRoleArn());
        request.setRoleSessionName("session-name");
        request.setDurationSeconds(3600L); // Token有效期，单位秒

        try {
            AssumeRoleResponse response = acsClient.getAcsResponse(request);
            return response.getCredentials();
        } catch (ClientException e) {
            log.error("Failed to get STS token", e);
            throw new StsTokenGenerateFailedException("Failed to get STS token", e);
        }
    }
}
