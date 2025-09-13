package dev.ramadhani.konfi.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.NoOpAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OrganizationAwareUsernameAndPasswordAuthenticationFilter organizationAwareAuthenticationFilter, AuthenticationManager authenticationManager) throws Exception {
        return http
                .anonymous(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizer -> {
//                    authorizer.requestMatchers("/api/v1/public/auth/login").permitAll();
                    authorizer.anyRequest().authenticated();
                })
                .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager)
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.authenticationEntryPoint(
                                    (req, res, authEx) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                            )
                            .accessDeniedHandler(
                                    (req, res, accessDeniedEx) -> res.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden")
                            );
                })
                .addFilterBefore(organizationAwareAuthenticationFilter,  UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public OrganizationAwareUsernameAndPasswordAuthenticationFilter organizationAwareAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtService jwtService, JwtConfiguration jwtConfiguration) {
        return new OrganizationAwareUsernameAndPasswordAuthenticationFilter(authenticationManager, objectMapper, jwtService, jwtConfiguration);
    }

    @Bean
    public AuthenticationManager providerManager(List<AuthenticationProvider> authenticationProviders) {
        return new ProviderManager(authenticationProviders);
    }

    @Bean
    public OrganizationAwareAuthenticationProvider organizationAwareAuthenticationProvider(UserService userService) {
        return new OrganizationAwareAuthenticationProvider(userService);
    }

    @Bean
    public UserService userService(UserRepository userRepository, OrganizationRepository organizationRepository, PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, organizationRepository, passwordEncoder);
    }

//    @Bean
//    public AuthenticationEntryPoint noopAuthenticationEntryPoint() {
//        return new NoOpAuthenticationEntryPoint();
//    }

}
