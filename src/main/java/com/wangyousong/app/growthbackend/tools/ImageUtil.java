package com.wangyousong.app.growthbackend.tools;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImageUtil {

    public static void main(String[] args) throws IOException {
        removeBlackBorder();
    }

    public static boolean removeBlackBorder() throws IOException {
        String inputFolder = "/Users/bob/Downloads/book_images_copy";
        String outputFolder = "/Users/bob/Downloads/book_images_handled_copy";

        List<File> images = Arrays.stream(Objects.requireNonNull(new File(inputFolder).listFiles()))
                .filter(it -> it.getName().endsWith(".jpg"))
                .toList();

        if (images.isEmpty()) {
            System.out.println("Input folder not found.");
            return false;
        }

        for (File imageFile : images) {
            String path = imageFile.getPath();
            BufferedImage image = ImageIO.read(new File(path));

            int width = image.getWidth();
            int height = image.getHeight();

            // Define crop area by removing 1 pixel from each side
            int croppedWidth = width - 2;
            int croppedHeight = height - 2;

            // Create a new BufferedImage to hold the cropped image
            BufferedImage croppedImage = new BufferedImage(croppedWidth, croppedHeight, BufferedImage.TYPE_INT_RGB);

            // Copy pixels from original image to cropped image, excluding border pixels
            for (int y = 0; y < croppedHeight; y++) {
                for (int x = 0; x < croppedWidth; x++) {
                    croppedImage.setRGB(x, y, image.getRGB(x + 1, y + 1)); // Offset by 1 pixel
                }
            }

            // Save the cropped image
            ImageIO.write(croppedImage, "jpg", new File(outputFolder + File.separator + imageFile.getName()));
        }
        return true;
    }


}
