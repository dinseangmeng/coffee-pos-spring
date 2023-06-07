package com.gic23.coffee_pos.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static String uploadPicture(MultipartFile file, String destinationDirectory) {
        if (file.isEmpty()) {
            return null; // File is empty, return null or handle the error as needed
        }

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = getFileExtension(fileName);

            // Generate a unique filename based on the current time in milliseconds
            String newFileName = System.currentTimeMillis() + "." + fileExtension;

            // Save the file to the specified destination directory
            String savedFileName = saveFile(file, newFileName, destinationDirectory);

            return savedFileName; // Return the saved filename

        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null or handle the error as needed
        }
    }

    private static String saveFile(MultipartFile file, String newFileName, String destinationDirectory)
            throws IOException {
        // Create the target directory if it doesn't exist
        File directory = new File(destinationDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Construct the target file path
        Path targetFilePath = Path.of(destinationDirectory, newFileName);

        // Save the file to the target location
        Files.copy(file.getInputStream(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);

        return newFileName; // Return the saved filename
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);

        if (file.exists()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
