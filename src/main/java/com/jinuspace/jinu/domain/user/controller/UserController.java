package com.jinuspace.jinu.domain.user.controller;

import com.jinuspace.jinu.domain.user.dto.JoinRequestDto;
import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.service.UserService;
import com.jinuspace.jinu.infrastructure.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "예제 API", description = "Swagger 테스트용 API")

public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${mode.secure:false}")
    private boolean secureMode;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        log.info("login in");
        Optional<User> authenticatedUser = userService.authenticate(loginRequestDto);

        if (authenticatedUser.isPresent()) {
            User user = authenticatedUser.get();
            String token = jwtUtil.createToken(user.getEmail(), user.getName(), user.getRole());
            log.info("token created -> {}", token);

            // ✅ Authorization 헤더에 JWT 포함
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token) // 헤더에 추가
                    .body(token); // Postman 테스트를 위해 응답 본문에도 포함
        } else {
            return ResponseEntity.badRequest().body("로그인 실패: 이메일 또는 비밀번호가 잘못되었습니다.");
        }
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody JoinRequestDto joinRequestDto) {
        try {
            User newUser = userService.join(joinRequestDto);
            return ResponseEntity.ok("User 등록 성공 : " + newUser.getEmail());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}