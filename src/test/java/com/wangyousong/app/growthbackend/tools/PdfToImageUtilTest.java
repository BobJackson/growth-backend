package com.wangyousong.app.growthbackend.tools;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class PdfToImageUtilTest {

    @Test
    void should_get_right_book_name() {
        String fullBookName = "Manning.Istio.in.Action.2022.3.pdf";
        String result = PdfToImageUtil.cutBookName(fullBookName);
        assertThat(result).isEqualTo("Manning.Istio.in.Action.2022.3");
    }

    @Test
    void should_convert_pdf_to_image() {
        String path = "/Users/bob/Desktop/books/it/OReilly.Optimizing.Java.2018.4.pdf";
        PdfToImageUtil.pdfToImage(new File(path));
    }
}