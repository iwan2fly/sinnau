package com.sinnau.sinnauApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sinnau.sinnauApplication", "com.sinnau.domain.board"})
public class SinnauApplication {
  public static void main(String[] args) {
    SpringApplication.run(SinnauApplication.class, args);
  }
}
