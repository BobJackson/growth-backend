package com.wangyousong.app.growthbackend;

import com.wangyousong.app.growthbackend.oss.config.AliYunOssRuntimeHints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(AliYunOssRuntimeHints.class)
public class GrowthBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowthBackendApplication.class, args);
    }

}
