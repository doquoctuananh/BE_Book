package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServiceBook {
    Page<Book> findAll(Pageable pageable);
    Book findById(int id);
    Book save(Book book);
    Book deleteById(int id);
    Book findByBookName(String bookName);
    Page<Book> searchByAuthorAndBookNameAndType( String author,
                                              String bookName,
                                                 Integer type ,
                                              Pageable pageable);
}
