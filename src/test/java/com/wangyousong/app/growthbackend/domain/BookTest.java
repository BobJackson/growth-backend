package com.wangyousong.app.growthbackend.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookTest {

    @Test
    void should_get_ext() {
        Book book = new Book();
        book.setCover("https://growth-public.oss-cn-shanghai.aliyuncs.com/books/it/OReilly.Design.Patterns.for.Cloud.Native.Applications.2021.5.jpg");

        String ext = book.extractFileExtension();
        assertThat(ext).isEqualTo(".jpg");
    }
}