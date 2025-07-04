package com.lakshan.carbonwise.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Date;

@Component
public class JWTService {

    private static String secretKey;

    @Value("${jwt.secret}")
    private String secretFromProperties;

    @PostConstruct
    public void init() {
        secretKey = secretFromProperties;
    }

    public String generateToken(String email, String name, String business) {
        long expirationMillis = 24 * 60 * 60 * 1000; // 1 day
        return Jwts.builder()
                .claim("email", email)
                .claim("name", name)
                .claim("business", business)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public static String getUserFromToken(String token) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("email", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}
