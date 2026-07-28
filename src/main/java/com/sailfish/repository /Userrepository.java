package com.sailfish.repository;

import com.sailfish.model.User; // Apke User model/entity ka package path yahan aayega
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Agar aap email ya username se user ko find karna chahte hain, toh yeh methods add kar sakte hain:
    User findByEmail(String email);
    User findByUsername(String username);
}
