package com.jinuspace.jinu.infrastructure.filter;

import com.jinuspace.jinu.infrastructure.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("JwtRequestFilter doFilterInternal");

        // ✅ 헤더에서 JWT 가져오기
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // "Bearer " 제거
        }

        log.info("JwtRequestFilter token: {}", token);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ JWT 검증
        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.extractEmail(token);
            String userName = jwtUtil.extractUserName(token);
            String role = jwtUtil.extractUserRole(token);

            UserDetails userDetails = new User(userName, "", Collections.emptyList());
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // ✅ 현재 요청 객체에 사용자 정보 저장
            request.setAttribute("email", email);
            request.setAttribute("userName", userName);
            request.setAttribute("role", role);
        }

        filterChain.doFilter(request, response);
    }
}