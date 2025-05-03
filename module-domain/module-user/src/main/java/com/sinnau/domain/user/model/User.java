package com.sinnau.domain.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users") // 테이블명 지정(선택)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password; // 반드시 암호화 저장

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private boolean enabled = true; // 활성화 여부(탈퇴/정지 등)

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column
    private LocalDateTime updatedAt;

    // 권한(역할) 필드 예시(선택)
    @Column(nullable = false)
    private String role = "USER";
}