package com.example.demo.model.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private String expiration;

    public Key createKeySecert(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // tao token tu username
    public String createToken(String username,String role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, createKeySecert())
                .compact();
    }

    // giai ma token
    public Claims extraToken(String token){
        Claims extraToken = null;
        extraToken = Jwts.parserBuilder()
                .setSigningKey(createKeySecert())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return extraToken;
    }

    // trich xuat user name
    public String getUserNameToken(String token){
        String username = null;
        username = extraToken(token).getSubject();
        return username;
    }

    // trich xuat role
    public String getRoleToken(String token){
        String role = null;
        role = (String) extraToken(token).get("role");
        return role;
    }

    //kiem tra thoi gian het han chua
    public boolean isTokenExpired(String token){
        boolean expiration = false;

        expiration = extraToken(token).getExpiration().getTime() < new Date().getTime();
        return expiration;
    }

    // kiem tra token hop le voi username khong
    public boolean verifyToken(String token,String username){
        boolean verified = false;
        String verifyUsename = getUserNameToken(token);
        verified = verifyUsename.equals(username) && !isTokenExpired(token);
        return verified;
    }


}
