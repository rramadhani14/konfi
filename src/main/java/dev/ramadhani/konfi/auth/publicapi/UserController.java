package dev.ramadhani.konfi.auth.publicapi;


import dev.ramadhani.konfi.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserPublicApiDtoToEntityMapper userDtoToEntityMapper;
    private final JwtConfiguration jwtConfiguration;
    private final JwtService jwtService;
//    @PostMapping("login")
//    public ResponseEntity<LoginResponsePayloadDto> login() {
//        OrganizationAwareUsernamePasswordAuthenticationToken organizationAwareUsernamePasswordAuthenticationToken = (OrganizationAwareUsernamePasswordAuthenticationToken) authentication;
//        return ResponseEntity.ok(new LoginResponsePayloadDto(jwtService.generateToken((String) organizationAwareUsernamePasswordAuthenticationToken.getPrincipal(), organizationAwareUsernamePasswordAuthenticationToken.getOrganizationId(), organizationAwareUsernamePasswordAuthenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(), null, jwtConfiguration.getAccessTokenValidityDuration())));
//        return ResponseEntity.badRequest().build();
//    }


//    @PostMapping("login")
//    public ResponseEntity<LoginResponsePayloadDto> login(Authentication authentication) {
//        OrganizationAwareUsernamePasswordAuthenticationToken organizationAwareUsernamePasswordAuthenticationToken = (OrganizationAwareUsernamePasswordAuthenticationToken) authentication;
//        return ResponseEntity.ok(new LoginResponsePayloadDto(jwtService.generateToken((String) organizationAwareUsernamePasswordAuthenticationToken.getPrincipal(), organizationAwareUsernamePasswordAuthenticationToken.getOrganizationId(), organizationAwareUsernamePasswordAuthenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(), null, jwtConfiguration.getAccessTokenValidityDuration())));
//    }


//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody UserPublicApiDto userPublicApiDto) {
//        try {
//            User user = userDtoToEntityMapper.dtoToEntity(userPublicApiDto);
//            user = userService.encodePasswordAndSaveUser(user);
//            return new ResponseEntity<>(user, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}
