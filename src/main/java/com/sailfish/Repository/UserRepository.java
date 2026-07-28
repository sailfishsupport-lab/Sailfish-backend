package com.sailfish.repository;

import com.sailfish.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@repository
public interface userrepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
}

