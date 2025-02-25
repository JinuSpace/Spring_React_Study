package com.jinuspace.jinu.domain.user.service;

import com.jinuspace.jinu.domain.user.dto.JoinRequestDto;
import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public Optional<User> authenticate(LoginRequestDto loginRequestDto) {
        log.info("UserService authenticate");
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());
        log.info("user founded ? -> {}", user.isPresent());
        if (user.isEmpty()) {
            return Optional.empty();  // 이메일이 없으면 Optional.empty() 반환
        }

        User authenticatedUser = user.get();

        // 비밀번호가 일치하면 유저 정보를 포함한 Optional 반환, 그렇지 않으면 Optional.empty() 반환
        if (passwordEncoder.matches(loginRequestDto.getPassword(), authenticatedUser.getPassword())) {
            log.info("Password matched");
            return Optional.of(authenticatedUser);  // 성공 시 유저 정보를 반환
        } else {
            log.info("Password not matched");
            return Optional.empty();  // 실패 시 Optional.empty() 반환
        }
    }

    public User join(JoinRequestDto joinRequestDto)throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(joinRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email is already registered.");
        }

        User newUser = User.builder().
                email(joinRequestDto.getEmail()).
                password(passwordEncoder.encode(joinRequestDto.getPassword())).
                name(joinRequestDto.getName()).
                role(joinRequestDto.getUserRole()).
                build();

        return userRepository.save(newUser);
    }
}