package com.example.demo.controller.admin;

import com.example.demo.model.DTO.DTOType;
import com.example.demo.model.Type;
import com.example.demo.service.Implements.ImplServiceType;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard/type")
public class ControllerType {

    @Autowired
    private ImplServiceType serviceType;

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Type>> getAll() {
        List<Type> listType = new ArrayList<>();
        listType = serviceType.findAllTypes();
        return ResponseEntity.status(HttpStatus.OK).body(listType);
    }


    @PostMapping ("/create")
    @Transactional
    public ResponseEntity<?> createType(@Valid @RequestBody DTOType dtoType,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldError().getDefaultMessage());
        }
        Type type = new Type();
        BeanUtils.copyProperties(dtoType, type);
        serviceType.saveType(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(type);
    }

    @Transactional
    @PatchMapping("edit/{id}")
    public ResponseEntity<?> editType(@PathVariable("id") int id,
                                      @Valid @RequestBody DTOType newDtoType,
                                      BindingResult bindingResult) {
//        System.out.println(id);
        if(bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        Type type = serviceType.findTypeById(id);
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found type with id " + id);
        }
        type.setTypeName(newDtoType.getTypeName());
        serviceType.saveType(type);
        return ResponseEntity.status(HttpStatus.OK).body(type);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<?> getTypeWithId(@PathVariable (name ="id") int id) {
        Type type = serviceType.findTypeById(id);
        if (type == null) {
            return ResponseEntity.badRequest().body("Not found type with id " + id);
        }
        return ResponseEntity.ok().body(type);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteType(@PathVariable("id") int typeId){
        Type type = serviceType.findTypeById(typeId);
        if (type == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found type book with id " + typeId);
        }
        serviceType.deleteTypeById(typeId);
        return ResponseEntity.status(HttpStatus.OK).body(type);
    }
}
