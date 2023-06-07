package com.gic23.coffee_pos.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileDownloadUtil {
    public static String downloadImage(String imageUrl, String destinationFolder) {
        String filename = System.currentTimeMillis() + ".jpg"; // Generate new filename based on current time
        Path destinationPath = Path.of(destinationFolder, filename);

        try (InputStream inputStream = new URL(imageUrl).openStream()) {

            // Copy the image data from the URL to the destination file
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            return destinationPath.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
