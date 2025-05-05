package com.sinnau.authapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.sinnau.authapi", "com.sinnau.domain.user"})
@EnableJpaRepositories(basePackages = "com.sinnau.domain.user.repository")
@EntityScan(basePackages = "com.sinnau.domain.user.model")
public class AuthApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthApiApplication.class, args);
  }
}
