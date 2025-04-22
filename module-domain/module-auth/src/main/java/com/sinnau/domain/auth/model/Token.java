package com.sinnau.domain.auth.model;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Token {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private LocalDateTime expirationTime;

    public String getFullAccessToken() {
        return tokenType + " " + accessToken;
    }
}