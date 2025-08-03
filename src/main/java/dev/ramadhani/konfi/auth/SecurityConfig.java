package dev.ramadhani.konfi.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatchers(matchers -> {
                    matchers.requestMatchers("/api/**");
                }).authorizeHttpRequests(authorizer -> {
                    authorizer.anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable)
                .build();

    }
}
