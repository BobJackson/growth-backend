package com.wangyousong.app.growthbackend.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

import static cn.hutool.crypto.SecureUtil.md5;

@Data
public class LoginRequest {
    @NotBlank(message = "username can not be blank")
    private String username;

    @NotBlank(message = "password can not be blank")
    private String password;

    public String md5Password() {
        return md5(password);
    }
}
