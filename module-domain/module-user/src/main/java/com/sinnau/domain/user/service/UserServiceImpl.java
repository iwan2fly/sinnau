package com.sinnau.domain.user.service;

import com.sinnau.domain.user.entity.User;
import com.sinnau.domain.user.exception.AlreadyRegisteredEmailException;
import com.sinnau.domain.user.repository.UserRepository;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public User registerUser(String email, String password, String name) {

    // 파라미터 체크
    if ( StringUtils.isEmpty( email ) ) throw new IllegalArgumentException( "email is empty" );
    if ( StringUtils.isEmpty( password ) ) throw new IllegalArgumentException( "password is empty" );
    if ( StringUtils.isEmpty( name ) ) throw new IllegalArgumentException( "name is empty" );

    // 이메일 중복 확인
    if (isEmailExists(email)) {
      throw new AlreadyRegisteredEmailException();
    }

    // 사용자 객체 생성
    User user = new User();
    user.setEmail(email);
    user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
    user.setName(name);
    user.setEnabled(true);
    //        user.setRole("USER"); // 기본 역할 설정
    user.setCreatedAt(LocalDateTime.now());

    log.debug( user.toString());

    // 저장 및 반환
    return userRepository.save(user);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }

  @Override
  public boolean isEmailExists(String email) {
    return userRepository.existsByEmail(email);
  }
}
