package com.sailfish.repository;

import com.sailfish.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends MongoRepository<User, String> {
    // Yeh MongoDB se apne aap data save aur fetch karne me madad karega
}
