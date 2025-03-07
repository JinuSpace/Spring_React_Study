package com.jinuspace.jinu.test.controller;


import com.jinuspace.jinu.test.entity.Test;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "예제 API", description = "Swagger 테스트용 API")

public class TestController {

    @GetMapping("/test")
    @Operation(summary = "Get member profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = Test.class))}),
            @ApiResponse(responseCode = "404", description = "해당 ID의 유저가 존재하지 않습니다."),
    })
    public String test() {
        log.info("=========Info 로그 찍힌다=============");
        return "테스트입니다.";
    }


}
