package com.sinnau.domain.auth.service;

import com.sinnau.domain.auth.model.Token;
import com.sinnau.domain.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/** JWT 토큰 생성 및 검증을 담당하는 서비스 클래스입니다. Access Token과 Refresh Token을 생성하고 관리합니다. */
@Service
public class TokenService {

  private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
  private static final Duration ACCESS_TOKEN_VALIDITY = Duration.ofHours(24); // 24시간
  private static final Duration REFRESH_TOKEN_VALIDITY = Duration.ofDays(7); // 7일

  /**
   * 사용자 정보를 기반으로 Access Token과 Refresh Token을 생성합니다.
   *
   * @param user 토큰을 생성할 사용자 정보
   * @return 생성된 Access Token과 Refresh Token을 포함한 Token 객체
   */
  public Token generateToken(User user) {

    LocalDateTime now = LocalDateTime.now();
    LocalDateTime accessTokenExpiry = now.plus(ACCESS_TOKEN_VALIDITY);
    LocalDateTime refreshTokenExpiry = now.plus(REFRESH_TOKEN_VALIDITY);

    Date nowDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    Date accessTokenExpiryDate =
        Date.from(accessTokenExpiry.atZone(ZoneId.systemDefault()).toInstant());
    Date refreshTokenExpiryDate =
        Date.from(refreshTokenExpiry.atZone(ZoneId.systemDefault()).toInstant());

    String accessToken =
        Jwts.builder()
            .setSubject(user.getEmail()) // JWT의 주제(sub)를 사용자의 email로 설정
            .claim(
                "roles",
                user.getRoles().stream().map(Object::toString).collect(Collectors.toList()))
            .setIssuedAt(nowDate)
            .setExpiration(accessTokenExpiryDate)
            .signWith(key)
            .compact();

    String refreshToken =
        Jwts.builder()
            .setSubject(user.getEmail())
            .setIssuedAt(nowDate)
            .setExpiration(refreshTokenExpiryDate)
            .signWith(key)
            .compact();

    return new Token(accessToken, refreshToken, "Bearer", accessTokenExpiry);
  }

  /**
   * JWT 토큰의 유효성을 검증하고 토큰에 포함된 Claims를 반환합니다.
   *
   * @param token 검증할 JWT 토큰 문자열
   * @return 토큰에서 추출한 Claims 객체
   * @throws io.jsonwebtoken.JwtException 토큰이 유효하지 않거나 만료된 경우
   */
  public Claims validateToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
  }

  /**
   * JWT 토큰에서 사용자 이름을 추출합니다.
   *
   * @param token 사용자 이름을 추출할 JWT 토큰
   * @return 토큰에서 추출한 사용자 이름
   * @throws io.jsonwebtoken.JwtException 토큰이 유효하지 않거나 만료된 경우
   */
  public String getUsernameFromToken(String token) {
    return validateToken(token).getSubject();
  }
}
