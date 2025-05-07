package com.sinnau.domain.user.service;

import com.sinnau.domain.user.entity.User;

public interface UserService {
  /**
   * 사용자 등록(회원가입)
   *
   * @param email 이메일
   * @param password 비밀번호 (암호화 전)
   * @param name 이름
   * @return 등록된 사용자 정보
   */
  User registerUser(String email, String password, String name);

  /**
   * 이메일로 사용자 조회
   *
   * @param email 이메일
   * @return 사용자 정보
   */
  User findByEmail(String email);

  /**
   * 이메일 중복 확인
   *
   * @param email 이메일
   * @return 중복 여부 (true: 중복, false: 사용 가능)
   */
  boolean isEmailExists(String email);
}
