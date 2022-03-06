package com.wangyousong.app.growthbackend.config.id;

import com.wangyousong.app.growthbackend.common.IdService;
import com.wangyousong.app.growthbackend.common.IdServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdServiceConfig {

    @Bean
    public IdService idService() {
        return new IdServiceImpl();
    }
}
