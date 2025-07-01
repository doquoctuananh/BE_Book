package com.example.demo.service;

import com.example.demo.model.User;

public interface IServiceUser {
    User findByUsername(String username);
}
