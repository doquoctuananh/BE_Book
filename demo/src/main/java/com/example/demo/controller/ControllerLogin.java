package com.example.demo.controller;

import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.Jwt.JwtResponse;
import com.example.demo.model.Jwt.JwtUtil;
import com.example.demo.model.Jwt.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.service.Implements.ImplServiceUser;
import com.example.demo.service.Implements.ImplUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class ControllerLogin {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ImplServiceUser implServiceUser;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        bCryptPasswordEncoder.encode(loginRequest.getPassword()))
        );
        User user = implServiceUser.findByUsername(loginRequest.getUsername());
        String token = jwtUtil.createToken(user.getUsername(), user.getRole().getName());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
