package com._205log.api.config;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.crypto.SecretKey;
import java.util.Base64;

@Data
@ConfigurationProperties(prefix = "leekwangho")
public class AppConfig {

    private SecretKey jwtKey;

    public void setJwtKey(String jwtKey) {
//        this.jwtKey = Base64.getDecoder().decode(jwtKey);
        this.jwtKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtKey));
    }

    public SecretKey getJwtKey() {
        return jwtKey;
    }
}
