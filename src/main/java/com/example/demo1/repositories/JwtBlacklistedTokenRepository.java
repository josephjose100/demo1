package com.example.demo1.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo1.entities.JwtBlacklistedToken;

public interface JwtBlacklistedTokenRepository extends JpaRepository<JwtBlacklistedToken, Long>{

	 Optional<JwtBlacklistedToken> findByAccessToken(String accessToken);

}
