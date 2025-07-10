package com.example.demo.service;

import com.example.demo.model.Role;

public interface IServiceRole {
    Role findByName (String name);
    Role save(Role role);
}
