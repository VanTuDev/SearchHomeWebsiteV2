package com.example.demo.Utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class ImageUtils {
    private static final Path IMAGE_DIR = Paths.get(ConstantUtils.IMAGE_PATH);

    public static Optional<String> upload(HttpServletRequest request) {
        Optional<String> imageName = Optional.empty();
        try {
            Part filePart = request.getPart("image");
            if (filePart.getSize() != 0 && filePart.getContentType().startsWith("image")) {
                if (!Files.exists(IMAGE_DIR)) {
                    Files.createDirectories(IMAGE_DIR);
                }
                Path targetLocation = Files.createTempFile(IMAGE_DIR, "img-", ".jpg");
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                }
                imageName = Optional.of(targetLocation.getFileName().toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageName;
    }
    public static List<String> uploadMultiple(HttpServletRequest request) {
        List<String> imageNames = new ArrayList<>();
        try {
            Collection<Part> fileParts = request.getParts();
            for (Part filePart : fileParts) {
                if (filePart.getName().equals("images[]") && filePart.getSize() != 0 && filePart.getContentType().startsWith("image")) {
                    if (!Files.exists(IMAGE_DIR)) {
                        Files.createDirectories(IMAGE_DIR);
                    }
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    Path targetLocation = IMAGE_DIR.resolve(fileName);

                    try (InputStream fileContent = filePart.getInputStream()) {
                        Files.copy(fileContent, targetLocation, StandardCopyOption.REPLACE_EXISTING);
                    }
                    imageNames.add(fileName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageNames; // Returns the list of file names that were uploaded
    }


    public static void delete( String imageName) {
        Path imagePath = IMAGE_DIR.resolve(imageName).normalize();
        try {
            boolean result = Files.deleteIfExists(imagePath);
            if (result) {
                System.out.println("File is deleted: " + imageName);
            } else {
                System.out.println("Sorry, unable to delete the file: " + imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

