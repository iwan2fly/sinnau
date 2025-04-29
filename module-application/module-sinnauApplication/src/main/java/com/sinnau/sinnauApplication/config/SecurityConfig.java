package com.sinnau.sinnauApplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth -> auth
                .requestMatchers("/", "/index.html", "/static/**", "/css/**", "/js/**", "/images/**", "/login", "/api/**", "/manifest.json", "/*.png", "/*.ico", "/asset-manifest.json").permitAll()
                .anyRequest().authenticated())
        .formLogin(form -> form.loginPage("/login").permitAll())
        .logout(logout -> logout.permitAll())
        .csrf(csrf -> csrf.disable());

    return http.build();
  }
}