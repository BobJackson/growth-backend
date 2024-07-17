package com.wangyousong.app.growthbackend.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PdfUtilsTest {

    @Test
    void should_remove_first_blank_page() {
        String inputPath = "/Users/bob/Downloads/SOLID.Introduction.to.Software.Design.and.Architecture.with.TypeScript.2022.pdf";
        String outputPath = "/Users/bob/Downloads/output.pdf";
        boolean removed = PdfUtils.removeFirstBlankPage(inputPath, outputPath);
        assertTrue(removed);
    }
}