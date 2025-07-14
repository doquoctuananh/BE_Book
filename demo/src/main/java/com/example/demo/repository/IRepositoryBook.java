package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryBook extends JpaRepository<Book, Integer> {

    Book save(Book book);
    Optional<Book> findById(int id);
    Optional<Book> deleteById(int id);
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findByBookName(String bookName);

    @Query("select b from Book b  " +
            "where (:author is null or b.author like %:author%)" +
            "and (:bookName is null or b.bookName like %:bookName%)" +
            "and (:type is null or b.type.id = :type)")
    Page<Book> searchByAuthorAndBookNameAndType(@Param("author") String author,
                                              @Param("bookName")String bookName,
                                              @Param("type") Integer type ,
                                              Pageable pageable);
    @Query("select b from Book b where " +
            " (:language is null or b.language like concat('%',:language,'%') ) " +
            " and (:minPrice is null  or :minPrice <= b.price ) " +
            "and (:maxPrice is null or b.price <= :maxPrice)" +
            " and (:type is null or b.type.id = :type)")
    Page<Book> searchBooksByLanguageAndPriceAndType(Pageable pageable,
                                                    @Param("language") String language,
                                                    @Param("minPrice") Float minPrice,
                                                    @Param("maxPrice") Float maxPrice,
                                                    @Param("type") Integer type);
}
