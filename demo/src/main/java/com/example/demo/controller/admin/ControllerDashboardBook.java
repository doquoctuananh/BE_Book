package com.example.demo.controller.admin;

import com.example.demo.model.Book;
import com.example.demo.service.Implements.ImplServiceBook;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/books")
public class ControllerDashboardBook {

    @Autowired
    private ImplServiceBook serviceBook;

    @GetMapping("")
    @Transactional
    public ResponseEntity<?> findAll(@RequestParam(name="page",defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 8);
        Page<Book> books = serviceBook.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/search")
    @Transactional
    public ResponseEntity<?> search(@RequestParam(name="author", required = false) String author,
                                    @RequestParam(name="bookName", required = false) String bookName,
                                    @RequestParam(name="type",required = false) Integer type,
                                    @RequestParam(name="page",defaultValue = "0") int page) {
        System.out.println(author);
        System.out.println(bookName);
        System.out.println(type);
        System.out.println(page);
        Pageable pageable = PageRequest.of(page, 6);
        Page<Book> books = serviceBook.searchByAuthorAndBookNameAndType(author, bookName,
                type, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
