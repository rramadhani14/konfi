package dev.ramadhani.konfi.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class OrganizationAwareUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected OrganizationAwareUsernamePasswordAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher, AuthenticationManager authenticationManager) {
        super(requiresAuthenticationRequestMatcher, authenticationManager);
    }
}
