package com.wangyousong.app.growthbackend.web.controller;

import com.wangyousong.app.growthbackend.common.R;
import com.wangyousong.app.growthbackend.service.UserService;
import com.wangyousong.app.growthbackend.web.request.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Value("${user.manage.token}")
    private String token;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public R<String> create(@RequestBody @Valid CreateUserRequest request, @RequestParam String token) {
        if (!Objects.equals(token, this.token)) {
            throw new IllegalArgumentException("token is not right!");
        }
        String userId = userService.create(request.getUsername(), request.getPassword());
        return R.success(userId);
    }
}
