package com.wangyousong.app.growthbackend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GrowthBackendApplicationTests {

    @Test
    void contextLoads() {
        assertThat(1 + 1).isEqualTo(2);
    }

}
