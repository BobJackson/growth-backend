package com.wangyousong.app.growthbackend.config.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aliyun.sts")
@Data
public class AliyunStsConfig {
    private String accessKeyId;
    private String accessKeySecret;
    private String roleArn;
    private String region;

    @Bean
    public IAcsClient acsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                this.getRegion(),
                this.getAccessKeyId(),
                this.getAccessKeySecret()
        );
        return new DefaultAcsClient(profile);
    }
}
