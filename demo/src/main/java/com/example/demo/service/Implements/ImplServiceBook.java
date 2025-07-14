package com.example.demo.service.Implements;

import com.example.demo.model.Book;
import com.example.demo.repository.IRepositoryBook;
import com.example.demo.service.IServiceBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplServiceBook implements IServiceBook {
    @Autowired
    private IRepositoryBook repositoryBook;


    @Override
    public Page<Book> findAll(Pageable pageable) {
        return repositoryBook.findAll(pageable);
    }

    @Override
    public Book findById(int id) {
        return repositoryBook.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return repositoryBook.save(book);
    }

    @Override
    public Book deleteById(int id) {
        return repositoryBook.deleteById(id).orElse(null);
    }

    @Override
    public Book findByBookName(String bookName) {
        return repositoryBook.findByBookName(bookName).orElse(null);
    }

    @Override
    public Page<Book> searchByAuthorAndBookNameAndType(String author, String bookName,
                                                       Integer type, Pageable pageable) {

        return repositoryBook.searchByAuthorAndBookNameAndType(author, bookName,
                type, pageable);
    }

    @Override
    public Page<Book> searchBooksByLanguageAndPriceAndType(Pageable pageable, String language, Float minPrice, Float maxPrice, Integer type) {
        return repositoryBook.searchBooksByLanguageAndPriceAndType(pageable, language,
                                                                minPrice, maxPrice, type);
    }
}
