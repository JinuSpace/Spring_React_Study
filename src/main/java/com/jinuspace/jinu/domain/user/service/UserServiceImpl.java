package com.jinuspace.jinu.domain.user.service;

import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User createUser(String name) {
        User newUser = new User();
        newUser.setName(name);
        return userRepository.save(newUser);
    }


}
