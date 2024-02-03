package com.jinuspace.jinu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/test")
    public String hello() {
        return "테스트입니다.";
    }
}
