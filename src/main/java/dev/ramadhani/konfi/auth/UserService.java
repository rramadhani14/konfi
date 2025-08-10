package dev.ramadhani.konfi.auth;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void createDefaultOrganizationsAndUser() {
        try {
            Organization organization = new Organization();
            organization.setName("admin");
            organization.setId("admin");
            organizationRepository.save(organization);
            User user = new User();
            user.setEmail("admin@test.com");
            user.setPassword("pleasechangeme");
            user.setRole(List.of(Role.GLOBAL_ADMIN));
            user.setOrganizationId(organization.getId());
            encodePasswordAndSaveUser(user);
            log.info("Created default organization with id {}", organization.getId());
            log.info("Created default admin user with id {}", user.getId());
        } catch (Exception e) {
            log.warn("Something went wrong while trying to save the default organization and user, either the organization and user are already created or something went wrong with mongodb.");
        }
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User encodePasswordAndSaveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return saveUser(user);
    }

    public Optional<User> findUserByUsernameAndOrganizationName(String email, String organizationName) {
        List<User> users = userRepository.findUsersByEmail(email);
        Optional<Organization> organization = organizationRepository.findOrganizationByName(organizationName);
        if(users.isEmpty() || organization.isEmpty()) {
            return Optional.empty();
        }
        return users.stream().filter(user -> Objects.equals(user.getOrganizationId(), organization.get().getId())).findFirst();
    }
}
