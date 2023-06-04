package com.gic23.coffee_pos.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Slf4j
public class ImageController {

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        log.info("Test: {}", filename);
        try {
            // Resolve the image file path
            Path imagePath = Paths.get("static/images/" + filename);
            Resource resource = new ClassPathResource(imagePath.toString());

            if (resource.exists()) {
                // Determine the media type based on the file extension
                MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(imagePath));

                // Return the image file as a ResponseEntity with the appropriate content type
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, mediaType.toString())
                        .body(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return a ResponseEntity with a status indicating the image file was not found
        return ResponseEntity.notFound().build();
    }
}
