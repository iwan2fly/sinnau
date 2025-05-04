package com.sinnau.domain.user.service;

import com.sinnau.domain.user.model.User;
import com.sinnau.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(String email, String password, String name) {
        // 이메일 중복 확인
        if (isEmailExists(email)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 사용자 객체 생성
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // 비밀번호 암호화
        user.setName(name);
        user.setEnabled(true);
        user.setRole("USER"); // 기본 역할 설정
        user.setCreatedAt(LocalDateTime.now());

        // 저장 및 반환
        return userRepository.save(user);
    }

    @Override
    public User authenticateUser(String email, String password) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElse(null);

        // 사용자가 존재하고 비밀번호가 일치하는지 확인
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        return null; // 인증 실패
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