package com.wangyousong.app.growthbackend.service.impl;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.wangyousong.app.growthbackend.config.aliyun.AliyunStsConfig;
import com.wangyousong.app.growthbackend.exception.StsTokenGenerateFailedException;
import com.wangyousong.app.growthbackend.service.StsTokenService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class StsTokenServiceImpl implements StsTokenService {

    @Resource
    private AliyunStsConfig stsConfig;
    @Resource
    private IAcsClient acsClient;

    @Override
    @Retryable(
            value = {java.net.UnknownHostException.class},
            maxRetries = 5,
            delay = 1500L)
    public AssumeRoleResponse.Credentials getStsToken() {
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setSysMethod(com.aliyuncs.http.MethodType.POST);
        request.setRoleArn(stsConfig.getRoleArn());
        request.setRoleSessionName("session-name");
        request.setDurationSeconds(3600L);

        try {
            AssumeRoleResponse response = acsClient.getAcsResponse(request);
            return response.getCredentials();
        } catch (ClientException e) {
            log.error("Failed to get STS token", e);
            throw new StsTokenGenerateFailedException("Failed to get STS token", e);
        }
    }
}
