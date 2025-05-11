package com.sinnau.domain.user.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user") // 테이블명 지정(선택)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false, unique = true, length = 64)
  private String email;

  @Column(nullable = false)
  private String password; // 반드시 암호화 저장

  @Column(nullable = false, length = 50)
  private String name;

  @Column(nullable = false)
  private boolean enabled = true; // 활성화 여부(탈퇴/정지 등)

  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  @Column private LocalDateTime updatedAt;

  // 권한(역할) 필드를 컬렉션으로 변경
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "userId"))
  @Column(name = "role", nullable = false)
  private List<String> roles = new ArrayList<>();

  // 기본 권한 추가 메서드
  public void addDefaultRole() {
    if (roles == null) {
      roles = new ArrayList<>();
    }
    if (roles.isEmpty()) {
      roles.add("USER");
    }
  }
}
