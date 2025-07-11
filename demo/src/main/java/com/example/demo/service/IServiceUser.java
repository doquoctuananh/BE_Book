package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServiceUser {
    User findByUsername(String username);
    User save(User user);
    User findByEmail(String email);
    User findByPhone(String phone);
    Page<User> findAll(Pageable pageable);
    List<User> getAllUsers();
    Page<User> searchUsersByNameAndPhoneAndEmail(Pageable pageable, String name,
                                                 String phone, String email);
}
