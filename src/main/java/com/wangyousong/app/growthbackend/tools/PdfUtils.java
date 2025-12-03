package com.wangyousong.app.growthbackend.tools;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

@UtilityClass
public class PdfUtils {

    @SneakyThrows
    public static boolean removeFirstBlankPage(String inputPath, String outputPath) {
        try (PDDocument document = Loader.loadPDF(new File(inputPath));
             PDDocument newDocument = new PDDocument()) {
            // 从第二页开始添加页面
            for (int i = 1, totalPages = document.getNumberOfPages(); i < totalPages; i++) {
                newDocument.addPage(document.getPage(i));
            }
            newDocument.save(outputPath);
        }
        return true;
    }

}
