package com.bookweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // ปิด CSRF เพื่อทดสอบเฉยๆ
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // อนุญาตทุก request ไม่ต้อง login
            );
        return http.build();
    }
}
