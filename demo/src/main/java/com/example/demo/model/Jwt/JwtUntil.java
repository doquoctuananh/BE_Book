package com.example.demo.model.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.security.Key;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtUntil {
    @Value("${jwt.secert}")
    private String key;

    @Value("${jwt.expiration}")
    private String expiration;

    public Key createKeySecert(){
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // tao token tu username
    public String createToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS512, createKeySecert())
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

    //kiem tra thoi gian het han chua
    public boolean getExpirationToken(String token){
        boolean expiration = false;

        expiration = extraToken(token).getExpiration().getTime() < new Date().getTime();
        return expiration;
    }

    // kiem tra token hop le voi username khong
    public boolean verifyToken(String token,String username){
        boolean verified = false;
        String verifyUsename = getUserNameToken(token);
        verified = verifyUsename.equals(username) && getExpirationToken(token);
        return verified;
    }


}
