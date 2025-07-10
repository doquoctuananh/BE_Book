package com.example.demo.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
//@RequestMapping("/")
public class ControllerHome {
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/api/home")
    public ResponseEntity<String> ApiHome() {
        return ResponseEntity.ok("Home Page");
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<?> image(@PathVariable("fileName") String fileName)
            throws MalformedURLException {
        Path path = Paths.get("upload/"+fileName);
        Resource reource = new UrlResource(path.toUri());
        if (!reource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(reource);
    }
}
