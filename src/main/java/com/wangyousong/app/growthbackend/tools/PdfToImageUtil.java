package com.wangyousong.app.growthbackend.tools;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Log4j2
public class PdfToImageUtil {
    /**
     * dpi越大转换后越清晰，相对转换速度越慢
     */
    private static final Integer DPI = 100;

    /**
     * 转换后的图片类型
     */
    private static final String IMG_TYPE = "jpg";
    public static final String BOOKS_DIR = "/Users/bob/Desktop/books/it";
    public static final String COVERS_DIR = "/Users/bob/Desktop/covers/it/";

    public static void main(String[] args) {
        File dir = new File(BOOKS_DIR);
        File[] pdfs = dir.listFiles();
        assert pdfs != null;
        StopWatch sw = new StopWatch();
        sw.start();
        Arrays.stream(pdfs).parallel().forEach(PdfToImageUtil::pdfToImage);
        sw.stop();
        log.debug("it cost " + sw.getTime(TimeUnit.MILLISECONDS) + "ms.");
    }

    @SneakyThrows
    public static void pdfToImage(File pdf) {
        if (!pdf.getName().contains(".pdf")) return;
        File dest = new File(COVERS_DIR + cutBookName(pdf.getName()) + "." + IMG_TYPE);
        if (dest.exists()) return;
        try (PDDocument document = Loader.loadPDF(pdf)) {
            PDFRenderer renderer = new PDFRenderer(document);
            for (int i = 0; i < 1; ++i) {
                BufferedImage bufferedImage = renderer.renderImageWithDPI(i, DPI);

                ByteArrayOutputStream out = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, IMG_TYPE, out);
                FileUtils.writeByteArrayToFile(dest, out.toByteArray());
            }
        }
    }

    static String cutBookName(String fullBookName) {
        return fullBookName.substring(0, fullBookName.lastIndexOf("."));
    }
}
