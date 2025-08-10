package dev.ramadhani.konfi.publicapi.config;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@Data
public class ConfigDto {
    private String name;
    private ConfigSourceType configSourceType = ConfigSourceType.MONGODB;
    private Map<String, Object> config;
}
