package com.wangyousong.app.growthbackend.web.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SubscribeController {
    @Value("${subscribe.url}")
    private String url;

    @GetMapping(value = "/subscribe", produces = "text/plain")
    public String subscribe() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
