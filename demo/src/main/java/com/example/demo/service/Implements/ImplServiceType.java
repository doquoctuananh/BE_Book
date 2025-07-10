package com.example.demo.service.Implements;

import com.example.demo.model.Type;
import com.example.demo.repository.IRepositoryType;
import com.example.demo.service.IServiceType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplServiceType implements IServiceType {

    @Autowired
    private IRepositoryType repositoryType;

    @Override
    public Type findTypeById(int id) {
        return repositoryType.findById(id).orElse(null);
    }

    @Override
    public Type findTypeByName(String name) {
        return repositoryType.findByTypeName(name).orElse(null);
    }

    @Override
    public Type saveType(Type type) {
        return repositoryType.save(type);
    }

    @Override
    public Type deleteTypeById(int id) {
        return repositoryType.deleteById(id);
    }

    @Override
    public List<Type> findAllTypes() {
        return repositoryType.findAll();
    }
}
