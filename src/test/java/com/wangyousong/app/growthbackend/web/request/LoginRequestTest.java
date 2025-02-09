package com.wangyousong.app.growthbackend.web.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginRequestTest {

    @Test
    void should_get_md5_password() {
        LoginRequest request = new LoginRequest();
        request.setUsername("Admin");
        request.setPassword("123456");

        assertThat(request.md5Password()).isNotEmpty();
    }
}