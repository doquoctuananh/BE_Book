package com.example.demo.controller;

import com.example.demo.model.DTO.DTOUser;
import com.example.demo.model.Jwt.JwtResponse;
import com.example.demo.model.Jwt.JwtUtil;
import com.example.demo.model.Jwt.LoginRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.Implements.ImplServiceRole;
import com.example.demo.service.Implements.ImplServiceUser;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class ControllerLoginAndRegister {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ImplServiceUser implServiceUser;
    @Autowired
    private ImplServiceRole implServiceRole;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword())
                );
                User user = implServiceUser.findByUsername(loginRequest.getUsername());
                String token = jwtUtil.createToken(user.getUsername(), user.getRole().getName());
                Map<String,String> tokenMap = new HashMap<>();
                tokenMap.put("token", token);
                tokenMap.put("role", user.getRole().getName());
                return ResponseEntity.ok().body(tokenMap);
            } catch (Exception ex) {
                Map<String, String> errors = new HashMap<>();
                errors.put("message", "Tài khoản hoặc mật khẩu không đúng");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
            }

    }

    @PostMapping("register")
    public ResponseEntity<?> resgister(@Valid @RequestBody DTOUser dtoUser,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getDefaultMessage(), error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }

        User checkUserName = implServiceUser.findByUsername(dtoUser.getUsername());
        User checkUserEmail = implServiceUser.findByEmail(dtoUser.getEmail());
        User checkPhone = implServiceUser.findByPhone(dtoUser.getPhone());
        System.out.println(dtoUser.getPhone()  );
        if (checkUserName != null || checkUserEmail != null || checkPhone != null) {
            Map<String, String> errors = new HashMap<>();
            if (checkUserName != null) {
                errors.put("username", "Username đã tồn tại vui lòng đặt lại ");
            }
            if(checkUserEmail !=null){
                errors.put("email","Email đã tồn tại vui lòng đặt lại");
            }
            if(checkPhone != null ){
                errors.put("phone","Số điện thoại đã tồn tại vui lòng đặt lại");
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
        }

        dtoUser.setPassword(bCryptPasswordEncoder.encode(dtoUser.getPassword().trim()));
        dtoUser.setUsername(dtoUser.getUsername().trim());

        Role role = implServiceRole.findByName("USER");
        User  user = new User();
        BeanUtils.copyProperties(dtoUser, user);
        user.setRole(role);
        implServiceUser.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
