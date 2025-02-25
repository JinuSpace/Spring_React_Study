package com.jinuspace.jinu.infrastructure.util;

import com.jinuspace.jinu.domain.user.entity.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-ms}")
    private Long expiredMs;

    // JWT 생성
    public String createToken(String email, String userName, UserRole role) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());


        return Jwts.builder()
                .claim("email", email)
                .claim("userName", userName)  // 사용자 이름 클레임 추가
                .claim("role", role)  // 역할(role) 클레임 추가
                .setIssuedAt(new Date())  // 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + expiredMs))  // 만료 시간 설정
                .signWith(key)  // 서명 키와 함께 서명
                .compact();  // JWT 생성
    }

    // JWT에서 사용자 이메일 추출
    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    // JWT에서 사용자 이름 추출
    public String extractUserName(String token) {
        return extractClaims(token).get("userName", String.class);
    }

    // JWT에서 역할(role) 추출
    public String extractUserRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    // JWT에서 만료 시간 추출
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // JWT에서 모든 클레임 추출
    private Claims extractClaims(String token) {
        log.error("token:{}", token);
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("JWT token is null or empty.");
        }

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
    }


    // JWT 만료 여부 확인
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // JWT 유효성 검증 (사용자 이름과 만료 여부를 기준으로)
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
