package com.wangyousong.app.growthbackend.web.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
}
