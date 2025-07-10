package com.example.demo.service.Implements;

import com.example.demo.model.Role;
import com.example.demo.repository.IRepositoryRole;
import com.example.demo.service.IServiceRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplServiceRole implements IServiceRole {

    @Autowired
    private IRepositoryRole repoRole;

    @Override
    public Role findByName(String name) {
        return repoRole.findByName(name);
    }

    @Override
    public Role save(Role role) {
        return repoRole.save(role);
    }
}
