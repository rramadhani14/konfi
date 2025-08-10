package dev.ramadhani.konfi.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public Optional<User> findUserByUsernameAndOrganizationName(String email, String organizationName) {
        List<User> users = userRepository.findUsersByEmail(email);
        Optional<Organization> organization = organizationRepository.findOrganizationByName(organizationName);
        if(users.isEmpty() || organization.isEmpty()) {
            return Optional.empty();
        }
        return users.stream().filter(user -> Objects.equals(user.getOrganizationId(), organization.get().getId())).findFirst();
    }
}
