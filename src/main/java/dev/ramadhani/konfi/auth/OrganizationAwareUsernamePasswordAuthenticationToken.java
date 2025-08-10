package dev.ramadhani.konfi.auth;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class OrganizationAwareUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String organizationId;

    public OrganizationAwareUsernamePasswordAuthenticationToken(Object principal, Object credentials, String organizationId) {
        super(principal, credentials);
        if(organizationId == null) {
            throw new IllegalArgumentException("organizationId cannot be null!");
        }
        this.organizationId = organizationId;
    }

    public OrganizationAwareUsernamePasswordAuthenticationToken(Object principal, Object credentials, String organizationId, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        if(organizationId == null) {
            throw new IllegalArgumentException("organizationId cannot be null!");
        }
        this.organizationId = organizationId;
    }
}
