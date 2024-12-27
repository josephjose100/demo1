package com.example.demo1.utils;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;
import static org.apache.commons.lang3.StringUtils.isBlank;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

  private static final SecureRandom RANDOM = new SecureRandom();

  // Generate 6 digit OTP number
  public static Integer get6digitUniqueNumber() {
    return RANDOM.nextInt(900000) + 100000;
  }

  public static String encode(String message, int iterations) {
    if (iterations <= 0) {
      iterations = 1;
    }
    Encoder encoder = getEncoder();
    while (iterations-- > 0) {
      message = encoder.encodeToString(message.getBytes());
    }
    return message;
  }

  public static String decode(String message, int iterations) {
    if (iterations <= 0) {
      iterations = 1;
    }
    Decoder decoder = getDecoder();
    while (iterations-- > 0) {
      message = new String(decoder.decode(message));
    }
    return message;
  }

  public static String generateSecureString() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[128];
    random.nextBytes(bytes);
    Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    return encoder.encodeToString(bytes);
  }

  public static String encryptPassword(String plainPassword) {
    if (isBlank(plainPassword)) {
      return plainPassword;
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);
    return encoder.encode(plainPassword);
  }

  public static Boolean matchEncryptedPassword(String plainPassword, String encryptedPassword) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(6);
    return encoder.matches(plainPassword, encryptedPassword);
  }

  public static String generateOtp(int length) {
    var randomizer = new SecureRandom();
    var builder = new StringBuilder();
    for (var i = 1; i <= length; i++) {
      builder.append(randomizer.nextInt(10));
    }

    return builder.toString();
  }

  @SuppressWarnings("unchecked")
  public static Map<String, Object> getPayloadFromJwt(String jwtToken) {
    if (StringUtils.isBlank(jwtToken)) {
      return Map.of();
    }
    String[] jwtTokenParts = jwtToken.split("\\.");
    if (jwtTokenParts.length != 3) {
      log.warn("Provided JWT Token is invalid");
      return Map.of();
    }
    try {
      String payload = decode(jwtTokenParts[1], 1);
      return new ObjectMapper().readValue(payload, Map.class);
    } catch (Exception e) {
      log.warn("Unable to convert JWT payload into Map. Reason : {}", e.getMessage());
      return Map.of();
    }
  }
}
