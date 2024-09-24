package com.jinuspace.jinu.domain.user.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponseDto {
    private String token;
}
