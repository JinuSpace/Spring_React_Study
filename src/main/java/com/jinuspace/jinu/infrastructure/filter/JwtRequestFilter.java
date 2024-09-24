package com.jinuspace.jinu.infrastructure.filter;

import com.jinuspace.jinu.infrastructure.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.Cookie;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 쿠키에서 토큰 가져오기
        String encodedToken = null;
        if (request.getCookies() != null) {
            encodedToken = Arrays.stream(request.getCookies())
                    .filter(cookie -> "jwt-token".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        System.out.println(encodedToken);
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedToken);
        String token = new String(decodedBytes);


        // 토큰이 있으면 검증 및 권한 설정
        if (token != null && jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 이름 및 역할 추출
            String username = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractUserRole(token);

            // 요청에 사용자 이름과 역할을 속성으로 추가 (다른 부분에서 사용 가능하게 설정)
            request.setAttribute("username", username);
            request.setAttribute("role", role);
        }

        // 다음 필터 또는 최종 목적지로 요청을 전달
        filterChain.doFilter(request, response);
    }
}