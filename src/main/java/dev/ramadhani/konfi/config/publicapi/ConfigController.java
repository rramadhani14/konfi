package dev.ramadhani.konfi.config.publicapi;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/public/v1/config")
@AllArgsConstructor
public class ConfigController {
    private ConfigService configService;
    private ConfigDtoToEntityMapper configMapper;

    @GetMapping("")
    public ResponseEntity<List<Config>> getConfigs() {
        return ResponseEntity.ok().body(configService.getConfigs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Config> getConfigById(@PathVariable String id) {
        Optional<Config> config = configService.getConfigById(id);
        return config.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Config> createConfig(@RequestBody ConfigDto configDto) {
        Config config = configMapper.dtoToEntity(configDto);
        return ResponseEntity.ok().body(configService.createConfig(config));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Config> updateConfig(@PathVariable String id, @RequestBody ConfigDto configDto) {
        Config config = configMapper.dtoToEntity(configDto);
        return ResponseEntity.ok().body(configService.updateConfig(id, config));
    }
}
