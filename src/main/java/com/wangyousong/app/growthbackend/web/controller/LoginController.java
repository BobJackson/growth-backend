package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.UserService;
import com.wangyousong.app.growthbackend.web.request.LoginRequest;
import com.wangyousong.app.growthbackend.web.response.LoginResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "login by username and password")
    public R<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return R.success(userService.login(request));
    }
}
