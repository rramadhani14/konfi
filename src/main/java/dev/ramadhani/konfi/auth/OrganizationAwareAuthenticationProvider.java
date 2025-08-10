package dev.ramadhani.konfi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizationAwareAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OrganizationAwareUsernamePasswordAuthenticationToken token = (OrganizationAwareUsernamePasswordAuthenticationToken) authentication;
        Optional<User> user = userService.findUserByUsernameAndOrganizationName(token.getName(), token.getOrganizationId());
        if(user.isEmpty()) {
            throw new BadCredentialsException("Username or Organization Not Found");
        }
        token.setAuthenticated(true);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OrganizationAwareUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
