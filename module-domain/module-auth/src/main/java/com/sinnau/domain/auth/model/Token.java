package com.sinnau.domain.auth.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
