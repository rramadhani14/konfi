package dev.ramadhani.konfi.config.publicapi;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, String> {
}
