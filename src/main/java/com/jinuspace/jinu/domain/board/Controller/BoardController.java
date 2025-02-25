package com.jinuspace.jinu.domain.board.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @GetMapping
    public ResponseEntity<String> getBoard(@AuthenticationPrincipal UserDetails userDetails) {
        // 역할이 ADMIN이면 게시판 접근 허용
        if ("ADMIN".equals(userDetails.getAuthorities())) {
            return ResponseEntity.ok("게시판 접근 허용됨");
        } else {
            return ResponseEntity.status(403).body("권한이 없습니다.");
        }
    }
}

