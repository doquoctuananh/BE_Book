package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryRole extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    Role save(Role role);
}
