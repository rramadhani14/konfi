package dev.ramadhani.konfi.auth.publicapi;


import dev.ramadhani.konfi.auth.User;
import dev.ramadhani.konfi.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/public/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserPublicApiDtoToEntityMapper userDtoToEntityMapper;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserPublicApiDto userPublicApiDto) {
        try {
            User user = userDtoToEntityMapper.dtoToEntity(userPublicApiDto);
            user = userService.encodePasswordAndSaveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
