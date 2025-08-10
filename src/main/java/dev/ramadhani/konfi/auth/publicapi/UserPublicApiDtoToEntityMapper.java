package dev.ramadhani.konfi.auth.publicapi;


import dev.ramadhani.konfi.auth.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPublicApiDtoToEntityMapper {
    User dtoToEntity(UserPublicApiDto userPublicApiDto);
    @Mapping(target = "password", ignore = true)
    UserPublicApiDto entityToDto(User user);
}
