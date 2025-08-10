package dev.ramadhani.konfi.auth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
interface UserRepository extends MongoRepository<User, String> {
    List<User> findUsersByEmail(String email);
}
