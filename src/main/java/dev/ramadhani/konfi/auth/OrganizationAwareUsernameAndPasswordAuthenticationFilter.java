package dev.ramadhani.konfi.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ramadhani.konfi.auth.publicapi.LoginResponsePayloadDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;


@AllArgsConstructor
public class OrganizationAwareUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    OrganizationAwareUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtService jwtService, JwtConfiguration jwtConfiguration) {
        super(authenticationManager);
        setRequiresAuthenticationRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher("/api/v1/public/auth/login"));
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            OrganizationAwareUsernamePasswordAuthenticationToken authenticationToken = (OrganizationAwareUsernamePasswordAuthenticationToken) authentication;
            LoginResponsePayloadDto payloadDTO = new LoginResponsePayloadDto(jwtService.generateToken((String) authenticationToken.getPrincipal(), authenticationToken.getOrganizationId(), authenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(), null, jwtConfiguration.getAccessTokenValidityDuration()));
            response.setHeader("Content-Type", "application/json");
            response.getWriter()
                    .write(objectMapper.writeValueAsString(payloadDTO));
        });
        setAuthenticationFailureHandler((request, response, exception) -> {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
        });

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String organizationId = request.getParameter("organizationId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(organizationId == null || organizationId.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new BadCredentialsException("Organization, username and password are mandatory!");
        }
        Authentication authentication = new OrganizationAwareUsernamePasswordAuthenticationToken(username, password, organizationId);

        return this.getAuthenticationManager().authenticate(authentication);
    }
}
