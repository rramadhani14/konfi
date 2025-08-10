package dev.ramadhani.konfi.publicapi.config;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigDtoToEntityMapper {
    Config dtoToEntity(ConfigDto configDto);
//    ConfigDto entityToDto(Config config);
}
