package com.example.demo.service;

import com.example.demo.model.ImportBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IServiceImportBook {
    Page<ImportBook> findAll(Pageable pageable);
    ImportBook findById(int id);
    ImportBook save(ImportBook importBook);
    Page<ImportBook> findByNameAndImportDate(@Param("name") String name, @Param("date") LocalDate date, Pageable pageable);

}
