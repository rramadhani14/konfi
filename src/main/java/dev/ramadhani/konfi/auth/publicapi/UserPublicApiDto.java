package dev.ramadhani.konfi.auth.publicapi;

import dev.ramadhani.konfi.auth.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserPublicApiDto {
    private String username;
    private String password;
    private String organizationId;
    private List<Role> role;
}
