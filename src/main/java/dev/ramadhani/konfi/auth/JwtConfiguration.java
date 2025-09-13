package dev.ramadhani.konfi.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;


@Configuration
@ConfigurationProperties(prefix = "konfi.auth.jwt")
@Data
public class JwtConfiguration {
    @NotBlank(message = "Secret need to be configured for authentication in konfi!")
    private String secret;
    @Pattern(regexp = "HS256", message = "Algorithm need to be configured for authentication in konfi! Currently only accept 'HS256'")
    private String algorithm;
    @NotNull(message = "JWT validity need to be configured for authentication in konfi!")
    private Duration accessTokenValidityDuration;
}
