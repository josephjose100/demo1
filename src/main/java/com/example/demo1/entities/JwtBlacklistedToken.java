package com.example.demo1.entities;

import java.time.ZonedDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jwt_blacklisted_tokens",
    indexes = {@Index(name = "jwt_bl_tokens_idx1_access_token", columnList = "access_token",
        unique = true)})
@Getter
@Setter
@ToString(callSuper = true)
public class JwtBlacklistedToken extends BaseEntity{

	
	  @Column(name = "access_token", length = 1500, nullable = false)
	  private String accessToken;

	  @Column(nullable = false)
	  private ZonedDateTime expiryDate;
}
