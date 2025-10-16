package com.project.back_end.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    // Tạo secret key để ký token (trong thực tế nên lưu trong file cấu hình)
    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // ✅ Hàm tạo JWT token
    public String generateToken(String email) {
        long now = System.currentTimeMillis();
        long expirationTime = 1000 * 60 * 60; // 1 giờ

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    // ✅ Hàm trả về key (tùy yêu cầu)
    public Key getSigningKey() {
        return secretKey;
    }
}
