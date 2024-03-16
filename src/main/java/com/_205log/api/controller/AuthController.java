package com._205log.api.controller;

import com._205log.api.config.AppConfig;
import com._205log.api.request.Login;
import com._205log.api.response.SessionResponse;
import com._205log.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppConfig appConfig;

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody Login login) {
        Long userId = authService.signin(login);

        String jws = Jwts.builder()
                .subject(String.valueOf(userId))
                .signWith(appConfig.getJwtKey())
                .issuedAt(new Date())
//                .expiration() // 만료 날짜, AuthResolver에서 만료여부 체크
                .compact();

        return new SessionResponse(jws);
    }
}
