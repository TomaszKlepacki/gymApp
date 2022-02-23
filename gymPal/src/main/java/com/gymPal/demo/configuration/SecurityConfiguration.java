package com.gymPal.demo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    String[] WHITELIST_SITES = {"/newUser","/verifyRegistration","/hello","/changePassword","/savePassword","/resetPassword"};

    /*@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(WHITELIST_SITES).permitAll();
        return http.build();
    }*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
