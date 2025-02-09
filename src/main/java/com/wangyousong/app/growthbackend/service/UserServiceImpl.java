package com.wangyousong.app.growthbackend.service;

import cn.hutool.core.lang.UUID;
import com.wangyousong.app.growthbackend.domain.User;
import com.wangyousong.app.growthbackend.repository.mongo.UserRepository;
import com.wangyousong.app.growthbackend.web.request.LoginRequest;
import com.wangyousong.app.growthbackend.web.response.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

import static cn.hutool.crypto.SecureUtil.md5;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setUsername(request.getUsername());

        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (optionalUser.isEmpty()) {
            log.info("Username is not exist!");
            return response;
        }

        Optional<User> optional = userRepository.findByUsernameAndPassword(request.getUsername(), request.md5Password());
        if (optional.isEmpty()) {
            log.info("Password is not right!");
            return response;
        }

        String token = UUID.fastUUID().toString();
        redisTemplate.opsForValue().set(token, optional.get());
        response.setToken(token);
        return response;
    }

    @Override
    public String create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5(password));

        User saved = userRepository.save(user);
        return saved.getId();
    }
}
