package com.jinuspace.jinu.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()  // CSRF 보호 비활성화 (주로 API 서버에서 사용)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/api/**").permitAll()  // /api 경로에 대해 인증 없이 접근 가능
//                        .anyRequest().authenticated()  // 그 외 요청은 인증 필요
//                )
//                .formLogin().disable();  // 기본 로그인 폼 비활성화
//
//        return http.build();
//    }
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    return httpSecurity
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configure(httpSecurity))
            .authorizeHttpRequests(requests -> {
                requests.requestMatchers("/api/login", "/api/join").permitAll();
                requests.requestMatchers(HttpMethod.POST, "/api/board").authenticated();
            })
            .sessionManagement(
                    sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}