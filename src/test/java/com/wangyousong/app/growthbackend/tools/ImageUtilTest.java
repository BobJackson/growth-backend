package com.wangyousong.app.growthbackend.tools;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ImageUtilTest {


    @Test
    void should_remove_black_border() throws IOException {
        boolean removed = ImageUtil.removeBlackBorder();
        assertTrue(removed);
    }
}