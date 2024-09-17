package com.jinuspace.jinu.domain.user.controller;

import com.jinuspace.jinu.domain.user.dto.JoinRequestDto;
import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "예제 API", description = "Swagger 테스트용 API")

public class UserController {
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