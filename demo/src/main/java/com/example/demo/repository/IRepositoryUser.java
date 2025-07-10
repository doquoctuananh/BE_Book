package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryUser extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
}
