package com.sailfish;

import com.sailfish.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Email ke zariye user ko dhoondne ke liye
    Optional<User> findByEmail(String email);
}
