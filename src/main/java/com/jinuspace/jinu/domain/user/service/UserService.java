package com.jinuspace.jinu.domain.user.service;

import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

        // 사용자가 존재하고, 비밀번호가 맞는지 확인
        if (user.isPresent() && user.get().getPassword().equals(loginRequestDto.getPassword())) {
            return true; // 로그인 성공
        }
        return false; // 로그인 실패
    }
}