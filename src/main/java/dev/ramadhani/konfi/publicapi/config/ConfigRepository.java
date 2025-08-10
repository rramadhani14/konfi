package dev.ramadhani.konfi.publicapi.config;


import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Config, String> {
}
