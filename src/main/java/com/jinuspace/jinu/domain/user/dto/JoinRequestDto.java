package com.jinuspace.jinu.domain.user.dto;

import com.jinuspace.jinu.domain.user.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDto {
    String email;
    String password;
    UserRole userRole;
}
