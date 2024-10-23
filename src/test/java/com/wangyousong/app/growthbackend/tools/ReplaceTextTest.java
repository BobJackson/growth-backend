package com.wangyousong.app.growthbackend.tools;

import org.junit.jupiter.api.Test;

class ReplaceTextTest {

    @Test
    void should_replace_text() {
        ReplaceText.main(new String[]{
                "Licensed to Thomas Hoang <minhtu.hoang19@gmail.com>",
                " ",
                "/Users/bob/Downloads/James Cutajar - Learn Concurrent Programming with Go-Manning (2024) copy.pdf",
                "/Users/bob/Downloads/Learn Concurrent Programming with Go.pdf"
        });
    }
}