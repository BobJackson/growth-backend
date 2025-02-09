package com.wangyousong.app.growthbackend.web.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String username;
}
