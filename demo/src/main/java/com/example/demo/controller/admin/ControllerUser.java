package com.example.demo.controller.admin;

import com.example.demo.model.User;
import com.example.demo.service.Implements.ImplServiceUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard/users")
public class ControllerUser {
    @Autowired
    private ImplServiceUser serviceUser;

    @GetMapping("")
    @Transactional
    public ResponseEntity<?> getUsers(@RequestParam(name="page",defaultValue = "0") int page) {
        System.out.println(page);
        Pageable pageable = PageRequest.of(page, 4);
        Page<User> listUser = serviceUser.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }
    @GetMapping("/statiscal")
    @Transactional
    public ResponseEntity<?> getUserStatiscal() {
        Map<String,String> data = new HashMap<>();
        List<User> listUser = serviceUser.getAllUsers();
        data.put("totalPerson",String.valueOf(listUser.size()));

        int countMale = (int) listUser.stream().filter(male -> male.isGender() == true).count();
        float percenMale = (float)countMale / listUser.size() * 100;
        data.put("personMale", String.valueOf(percenMale));

        int countAccountActive = (int) listUser.stream().filter(account -> account.getStatus().equals("active")).count();
        float percenAccountActive = (float) countAccountActive / listUser.size() * 100;
        data.put("personAccountActive", String.valueOf(percenAccountActive));

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping("/search")
    @Transactional
    public ResponseEntity<?> search(@RequestParam(name = "page",defaultValue = "0")int page,
                                    @RequestParam(name = "username",required= false) String username,
                                    @RequestParam(name = "phone",required= false) String phone,
                                    @RequestParam(name = "email",required= false) String email) {

        Pageable pageable = PageRequest.of(page, 4);
        Page<User> searchListUser = serviceUser.searchUsersByNameAndPhoneAndEmail(pageable, username, phone, email);
//        if(searchListUser.getTotalElements() > 0) {
//            Map<String,String> errors = new HashMap<>();
//            errors.put("message", "Username already exists");
//        }
        return ResponseEntity.status(HttpStatus.OK).body(searchListUser);
    }
}
