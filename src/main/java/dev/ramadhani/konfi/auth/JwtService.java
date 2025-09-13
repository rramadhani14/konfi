package dev.ramadhani.konfi.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class JwtService {
    private JwtConfiguration jwtConfiguration;
    private final JWTVerifier jwtVerifier;
    private final Algorithm algorithm;

    JwtService(JwtConfiguration jwtConfiguration) {
        algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
        jwtVerifier = JWT.require(algorithm)
                .withIssuer("konfi")
                .build();
    }


    public String generateToken(String username, String organizationId, List<String> roles, Map<String, String> customClaims, Duration expireIn) {
        JWTCreator.Builder builder = JWT.create()
                .withClaim("username", username)
                .withClaim("organizationId", organizationId)
                .withClaim("roles", roles);
        if(customClaims != null) {
            customClaims.forEach(builder::withClaim);
        }
        return builder
                .withExpiresAt(Instant.now().plus(expireIn))
                .withIssuer("konfi")
                .sign(algorithm);
    }

    public DecodedJWT readToken(String token) {
        return jwtVerifier.verify(token);
    };

    public Claim extractClaim(DecodedJWT token, String name) {
        return token.getClaim(name);
    }
}
