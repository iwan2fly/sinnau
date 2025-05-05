package com.sinnau.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sinnau.authapi", "com.sinnau.domain.user"})
@EnableJpaRepositories(basePackages = "com.sinnau.domain.user.repository")
@EntityScan(basePackages = "com.sinnau.domain.user.model")
public class AuthApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthApiApplication.class, args);
  }
}
