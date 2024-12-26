package com.example.demo1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String name);
}
