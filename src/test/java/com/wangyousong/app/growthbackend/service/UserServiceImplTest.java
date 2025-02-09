package com.wangyousong.app.growthbackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void should_create_user() {
        String username = "whoever";
        String password = "whatever";
        String id = userService.create(username, password);
        assertThat(id).isNotEmpty();
    }
}