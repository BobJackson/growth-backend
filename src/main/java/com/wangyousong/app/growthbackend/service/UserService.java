package com.wangyousong.app.growthbackend.service;

import com.wangyousong.app.growthbackend.web.request.LoginRequest;
import com.wangyousong.app.growthbackend.web.response.LoginResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);

    String create(String username, String password);
}
