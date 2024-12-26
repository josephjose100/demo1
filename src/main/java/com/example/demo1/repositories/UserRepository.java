package com.example.demo1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstNameOrEmail(String username, String email);
    Optional<User> findByFirstName(String username);
    Boolean existsByFirstName(String username);
    Boolean existsByEmail(String email);
}
