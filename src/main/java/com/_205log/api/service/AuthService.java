package com._205log.api.service;

import com._205log.api.domain.Session;
import com._205log.api.domain.User;
import com._205log.api.exception.InvalidSigninInformation;
import com._205log.api.repository.UserRepository;
import com._205log.api.request.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public String signin(Login login) {
        User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword())
                .orElseThrow(InvalidSigninInformation::new);

        Session session = user.addSession();

        return session.getAccessToken();
    }
}
