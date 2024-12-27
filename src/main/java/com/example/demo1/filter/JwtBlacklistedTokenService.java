package com.example.demo1.filter;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo1.entities.JwtBlacklistedToken;
import com.example.demo1.repositories.JwtBlacklistedTokenRepository;
import com.example.demo1.utils.DateTimeUtils;
import com.example.demo1.utils.SecurityUtils;

@Service
public class JwtBlacklistedTokenService {

  @Autowired
  private JwtBlacklistedTokenRepository jwtBlacklistedTokenRepository;

  @Transactional
  public void createBlacklistedToken(String accessToken) {
    Map<String, Object> payload = SecurityUtils.getPayloadFromJwt(accessToken);
    JwtBlacklistedToken jwtBlacklistedToken = new JwtBlacklistedToken();
    jwtBlacklistedToken.setAccessToken(accessToken);
    jwtBlacklistedToken.setExpiryDate(
        DateTimeUtils.convertLongToZonedDateTime(((Integer) payload.get("exp")).longValue()));
    jwtBlacklistedTokenRepository.save(jwtBlacklistedToken);
  }

}

