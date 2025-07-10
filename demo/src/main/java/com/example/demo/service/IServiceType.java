package com.example.demo.service;

import com.example.demo.model.Type;

import java.util.List;

public interface IServiceType {
    Type findTypeById(int id);
    Type findTypeByName(String name);
    Type saveType(Type type);
    Type deleteTypeById(int id);
    List<Type> findAllTypes();
}
