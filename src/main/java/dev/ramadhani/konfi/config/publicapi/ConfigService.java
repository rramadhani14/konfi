package dev.ramadhani.konfi.config.publicapi;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfigService {
    private ConfigRepository configRepository;

    List<Config> getConfigs() {
        return configRepository.findAll();
    }

    Optional<Config> getConfigById(String id) {
        return configRepository.findById(id);
    }

    Config createConfig(Config config) {
        return configRepository.save(config);
    }

    Config updateConfig(String id, Config config) {
        config.setId(id);
        return configRepository.save(config);
    }
}
