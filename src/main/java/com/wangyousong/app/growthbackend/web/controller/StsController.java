package com.wangyousong.app.growthbackend.web.controller;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.wangyousong.app.growthbackend.service.StsTokenService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sts")
public class StsController {

    @Resource
    private StsTokenService stsTokenService;

    @GetMapping("/token")
    public AssumeRoleResponse.Credentials getStsToken() {
        return stsTokenService.getStsToken();
    }
}
