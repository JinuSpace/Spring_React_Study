package com.jinuspace.jinu.domain.user.service;

import com.jinuspace.jinu.domain.user.dto.JoinRequestDto;
import com.jinuspace.jinu.domain.user.dto.LoginRequestDto;
import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public boolean authenticate(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

        return user.isPresent() && passwordEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword());
    }

    public User join(JoinRequestDto joinRequestDto)throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(joinRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email is already registered.");
        }

        User newUser = User.builder().
                email(joinRequestDto.getEmail()).
                password(passwordEncoder.encode(joinRequestDto.getPassword())).
                role(joinRequestDto.getUserRole()).
                build();

        return userRepository.save(newUser);
    }
}