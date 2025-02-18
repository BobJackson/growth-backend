package com.wangyousong.app.growthbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GrowthBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthBackendApplication.class, args);
    }

}
