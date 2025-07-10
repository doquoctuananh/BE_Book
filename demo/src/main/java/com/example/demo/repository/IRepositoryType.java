package com.example.demo.repository;

import com.example.demo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRepositoryType extends JpaRepository<Type, Integer> {
    Optional<Type> findByTypeName(String name);
    Optional<Type> findById(int id);
    Type save(Type type);
    Type deleteById(int id);
    List<Type> findAll();
}
