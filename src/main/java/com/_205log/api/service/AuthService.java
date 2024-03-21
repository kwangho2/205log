package com._205log.api.service;

import com._205log.api.crypto.PasswordEncoder;
import com._205log.api.crypto.ScryptPasswordEncoder;
import com._205log.api.domain.User;
import com._205log.api.exception.AlreadyExistsEmailException;
import com._205log.api.exception.InvalidSigninInformation;
import com._205log.api.repository.UserRepository;
import com._205log.api.request.Login;
import com._205log.api.request.Signup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long signin(Login login) {
        User user = userRepository.findByEmail(login.getEmail())
                .orElseThrow(InvalidSigninInformation::new);

        var matches = passwordEncoder.matches(login.getPassword(), user.getPassword());

        if (!matches) {
            throw new InvalidSigninInformation();
        }

        return user.getId();
    }

    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByEmail(signup.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        String encryptedPassword = passwordEncoder.encrypt(signup.getPassword());

        var user = User.builder()
                .email(signup.getEmail())
                .name(signup.getName())
                .password(encryptedPassword)
                .build();

        userRepository.save(user);
    }
}
