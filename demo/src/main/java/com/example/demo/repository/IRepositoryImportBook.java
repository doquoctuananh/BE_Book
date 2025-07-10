package com.example.demo.repository;

import com.example.demo.model.ImportBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IRepositoryImportBook extends JpaRepository<ImportBook, Integer> {
    ImportBook save(ImportBook importBook);
    Optional<ImportBook> findById(int id);
    ImportBook deleteById(int id);
    Page<ImportBook> findAll(Pageable pageable);

    @Query("select ipb from ImportBook ipb where " +
            " (:name is  null or ipb.name like %:name% ) " +
            " AND (:date is null or ipb.importDate =:date)")
    Page<ImportBook> findByNameAndImportDate(@Param("name") String name, @Param("date")LocalDate date, Pageable pageable);
}
