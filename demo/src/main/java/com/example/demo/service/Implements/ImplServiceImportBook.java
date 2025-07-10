package com.example.demo.service.Implements;

import com.example.demo.model.ImportBook;
import com.example.demo.repository.IRepositoryImportBook;
import com.example.demo.service.IServiceImportBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ImplServiceImportBook  implements IServiceImportBook {
    @Autowired
    private IRepositoryImportBook repositoryImportBook;

    @Override
    public Page<ImportBook> findAll(Pageable pageable) {
        return repositoryImportBook.findAll(pageable);
    }

    @Override
    public ImportBook findById(int id) {
        return repositoryImportBook.findById(id).orElse(null);
    }

    @Override
    public ImportBook save(ImportBook importBook) {
        return repositoryImportBook.save(importBook);
    }

    @Override
    public Page<ImportBook> findByNameAndImportDate(String name, LocalDate date, Pageable pageable) {
        return repositoryImportBook.findByNameAndImportDate(name, date, pageable);
    }
}

