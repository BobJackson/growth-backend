// src/main/java/com/example/demo/controller/StsController.java
package com.wangyousong.app.growthbackend.web.controller;

import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.wangyousong.app.growthbackend.service.StsTokenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
