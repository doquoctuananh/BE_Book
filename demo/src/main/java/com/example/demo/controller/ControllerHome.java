package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.Type;
import com.example.demo.service.Implements.ImplServiceBook;
import com.example.demo.service.Implements.ImplServiceType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
//@RequestMapping("/")
public class ControllerHome {
    @Autowired
    private ImplServiceType serviceType;
    @Autowired
    private ImplServiceBook serviceBook;

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

    @GetMapping("/api/home/category")
    public ResponseEntity<?> categoryHome() {
        List<Type> listType = serviceType.findAllTypes();
        return ResponseEntity.status(HttpStatus.OK).body(listType);
    }

    @GetMapping("/api/home/books")
    @Transactional
    public ResponseEntity<?> booksHome(@RequestParam(name="page",defaultValue = "0") int page
                                       ) {
        Pageable pageable = PageRequest.of(page, 9);
        Page<Book> pageBook = serviceBook.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pageBook);
    }

    @GetMapping("/api/home/books/filter")
    @Transactional
    public ResponseEntity<?> bookFilter(@RequestParam(name = "page",defaultValue = "0") int page,
                                        @RequestParam(name = "language",required = false) String language,
                                        @RequestParam(name = "minPrice",required = false) Float minPrice,
                                        @RequestParam(name = "maxPrice",required = false) Float maxPrice,
                                        @RequestParam(name = "typeId",required = false) Integer typeId)  {
        System.out.println(language);
        System.out.println(typeId);
        System.out.println(minPrice);
        System.out.println(maxPrice);
        Pageable pageable = PageRequest.of(page, 9);
        Page<Book> pageBook = serviceBook.searchBooksByLanguageAndPriceAndType(pageable,language,
            minPrice,maxPrice,typeId);
    return ResponseEntity.status(HttpStatus.OK).body(pageBook);
    }

}

