package dev.ramadhani.konfi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
            throw new UsernameNotFoundException("User not found");
        }
        User userDetails = user.get();
        return new OrganizationAwareUsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getOrganizationId(), userDetails.getRole().stream().map(it -> new SimpleGrantedAuthority(it.name())).toList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OrganizationAwareUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
