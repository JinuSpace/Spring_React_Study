package com.jinuspace.jinu;

import com.jinuspace.jinu.domain.user.entity.User;
import com.jinuspace.jinu.domain.user.repository.UserRepository;
import com.jinuspace.jinu.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        // 사용자 생성
        String userName = "Test User";
        User createdUser = userService.createUser(userName);

        // 생성된 사용자 검증
        assertThat(createdUser).isNotNull();
        assertThat(createdUser.getName()).isEqualTo(userName);

        // 데이터베이스에 사용자가 실제로 저장되었는지 확인
        User foundUser = userRepository.findById(createdUser.getId()).orElse(null);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo(userName);
    }
}