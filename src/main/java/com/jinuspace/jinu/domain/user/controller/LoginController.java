package com.jinuspace.jinu.domain.user.controller;

import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.service.UserService;
import com.jinuspace.jinu.test.entity.Test;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "예제 API", description = "Swagger 테스트용 API")

public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        boolean isAuthenticated = userService.authenticate(loginRequestDto);

        if (isAuthenticated) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }
}