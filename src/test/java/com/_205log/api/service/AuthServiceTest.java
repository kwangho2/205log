package com._205log.api.service;

import com._205log.api.domain.User;
import com._205log.api.exception.AlreadyExistsEmailException;
import com._205log.api.repository.UserRepository;
import com._205log.api.request.Signup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @AfterEach
    void clean() {userRepository.deleteAll();}

    @Test
    @DisplayName("회원가입 성공")
    void test1() {
        // given
        Signup signup = Signup.builder()
                .password("1234")
                .email("205@gmail.com")
                .name("205")
                .build();

        // when
        authService.signup(signup);

        // then
        assertEquals(1, userRepository.count());

        User user = userRepository.findAll().iterator().next();
        assertEquals("205@gmail.com", user.getEmail());
        assertEquals("1234", user.getPassword());
        assertEquals("205", user.getName());
    }

    @Test
    @DisplayName("회원가입시 중복된 이메일")
    void test2() {
        // given
        User user = User.builder()
                .email("205@gmail.com")
                .password("1234")
                .name("206")
                .build();
        userRepository.save(user);


        Signup signup = Signup.builder()
                .password("1234")
                .email("205@gmail.com")
                .name("205")
                .build();

        // expected
        assertThrows(AlreadyExistsEmailException.class, () -> authService.signup(signup));
    }
}