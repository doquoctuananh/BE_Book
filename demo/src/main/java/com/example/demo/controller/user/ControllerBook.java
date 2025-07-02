package com.example.demo.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class ControllerBook {
    @GetMapping("/")
    public ResponseEntity<String> getBooks() {
        return ResponseEntity.ok().body("book page");
    }
}
