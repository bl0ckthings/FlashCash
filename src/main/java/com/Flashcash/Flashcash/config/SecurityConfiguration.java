package com.Flashcash.Flashcash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/assets/**", "index.css", "/images/**",  "/auth/signin",
                                "/auth/signup")
                        .permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) ->

                        form
                                .loginPage("/auth/signin").permitAll()
                                .usernameParameter("email")
                                .defaultSuccessUrl("/", true)

                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
}
