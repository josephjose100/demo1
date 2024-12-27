package com.example.demo1.services;

import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo1.entities.Role;
import com.example.demo1.entities.User;
import com.example.demo1.repositories.UserRepository;

import java.io.StringReader;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.bouncycastle.*;

@Service
@Slf4j
public class jwtTokenService {

	@Autowired
	private UserRepository userRepository;
//	jwt-signing-key: -----BEGIN RSA PRIVATE KEY-----MIIEpAIBAAKCAQEAxnicFNoPqs3b/CjBLLB6BvbxpPvT03g6fpGwM6goo5aqFvgRL4R4mfYDXuDZzOzhGke6z1c+uxC4uLf4EWAKLIjWDW9xNXxvBWIMGCHxh/L3v2elJgqh7I5I75PtwUXlokzeMJbCNXTu0fKMVaEaqnD0LizHsU6e4w9Zwgw2Rhyj/7V+XcYJYDTf8rAroQ7rIp+XwLAlDle3/OOlAjjtHO9S9aPfQlTdb9sRTXcvCkkEI2L3xQ5iRuBBJC7kSbgdF5ZAlZ7qvsFokXPXTfIATd2zzvs7F/AmiOuWr8vTDT/y2IB9eS4A2wYdMYJwRT2ClMksVRpitUvaV09vSAJIpQIDAQABAoIBAFi+ZUwNlSPSmKLPtlC9OFMU6EfVMopEICOoERmH/2PmYneSxFxy51owXqF1+O1gneaaXDWJ1Urf1zkBSbK7NtFi/hNT2VMJKLtHdBD2XIFlatHzDi1MY8radFJr2suo7Vz88fzX7E7q0j6eLZG8T6gWiZh7/W6SsQjmPERAIaSHLvalrHbrBuAN4/oBHU5O4LC0NVDZN7uweXs/e/IGPw31c2iMI1PKa9NmmKly+l3Owltx55MqjhIZT5x6PjQBTBkrhbrdA+/jYtR2tLYK13uWQCShaYjUuRTSFyntlvV6JjJg5JIQMzg4So7xeaKevNS+vNjaeYXMbIP8ylQB+gECgYEA/W2/FSbJ+uCiSN6+GGMATTih8jmYxx19JfwtzBnAuG+Scl/HkBWM5bedIlVu82tP/ckPthP9z1mNpS3zZnXTzU/tOq9EtGOXjHNi6zRKarvum2AD71ijgWtZaTvBmdtXkPLwMBpHCYVR43kRqabvvCg2MbL3p7kQI/c1OoDGtvECgYEAyHwd8MHtMJOoo7qDqHVfNOA7y1zkRhDuO8xbEdqK7SUckX1VdhprlDfWHTwAX0HNV+SYsu+YkLhIdeRlDS712kbKOr/R8OoXLQyUpqjt1+hV3kLsRQS2pgK0zqZjQMk0qwetUKEvLyvzPYBJASYX04hbwOjMZdv2gMSFJk3wdPUCgYEApJ7Eb+3pZ/DBGgquetLcYEz4TYsCZd0O7Ty5VwoneObovyjeTgbhplq2VJYgYziDFnyjmwezaBxo+TI3GIhTU9umYud0/qRuzgop6FToPhrjrMQa6j+uviPISaZKZTHo2LwmL2jyWgnjHpsHUSeiNJv+UBxL6QQ6qtIrHlyGOFECgYEAhOvdgfFhS/KqPZt8jOT8sXb1zfgRlO7GtLjhrG2j7GTNkxxw2/PaXZPDjvBoIr/i4CI8p245TcIQsLEz2lDHSefjTp49GChsIz8TE4gu4RY2UD04nu3oFxr2O4iPh7WfCMH3Q90KBpFyHNWAEZXq+CGRC86NLf9vaKJi0Smdgj0CgYA2uRvS7UVaDFl6BrHFLxQS7UJoUiHhO5zha+hm6wKRPD41rgsHPyx/stxzsL1jUQSIA3LG/N2jDSYsGuiIzYB0Fp3js43bXOlLyJs6A4VeXyV/cFuj9b31/+r9OZOSp7zrHTXPYnHnlFYMKLuy+D4b70dBldPaEB1ScWvaHp6KHw==-----END RSA PRIVATE KEY-----
//    jwt-public-key: AAAAB3NzaC1yc2EAAAADAQABAAABAQDGeJwU2g+qzdv8KMEssHoG9vGk+9PTeDp+kbAzqCijlqoW+BEvhHiZ9gNe4NnM7OEaR7rPVz67ELi4t/gRYAosiNYNb3E1fG8FYgwYIfGH8ve/Z6UmCqHsjkjvk+3BReWiTN4wlsI1dO7R8oxVoRqqcPQuLMexTp7jD1nCDDZGHKP/tX5dxglgNN/ysCuhDusin5fAsCUOV7f846UCOO0c71L1o99CVN1v2xFNdy8KSQQjYvfFDmJG4EEkLuRJuB0XlkCVnuq+wWiRc9dN8gBN3bPO+zsX8CaI65avy9MNP/LYgH15LgDbBh0xgnBFPYKUySxVGmK1S9pXT29IAkil

	 // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
 
	//public static final String privateKeyString = "-----BEGIN RSA PRIVATE KEY-----MIIEpAIBAAKCAQEAxnicFNoPqs3b/CjBLLB6BvbxpPvT03g6fpGwM6goo5aqFvgRL4R4mfYDXuDZzOzhGke6z1c+uxC4uLf4EWAKLIjWDW9xNXxvBWIMGCHxh/L3v2elJgqh7I5I75PtwUXlokzeMJbCNXTu0fKMVaEaqnD0LizHsU6e4w9Zwgw2Rhyj/7V+XcYJYDTf8rAroQ7rIp+XwLAlDle3/OOlAjjtHO9S9aPfQlTdb9sRTXcvCkkEI2L3xQ5iRuBBJC7kSbgdF5ZAlZ7qvsFokXPXTfIATd2zzvs7F/AmiOuWr8vTDT/y2IB9eS4A2wYdMYJwRT2ClMksVRpitUvaV09vSAJIpQIDAQABAoIBAFi+ZUwNlSPSmKLPtlC9OFMU6EfVMopEICOoERmH/2PmYneSxFxy51owXqF1+O1gneaaXDWJ1Urf1zkBSbK7NtFi/hNT2VMJKLtHdBD2XIFlatHzDi1MY8radFJr2suo7Vz88fzX7E7q0j6eLZG8T6gWiZh7/W6SsQjmPERAIaSHLvalrHbrBuAN4/oBHU5O4LC0NVDZN7uweXs/e/IGPw31c2iMI1PKa9NmmKly+l3Owltx55MqjhIZT5x6PjQBTBkrhbrdA+/jYtR2tLYK13uWQCShaYjUuRTSFyntlvV6JjJg5JIQMzg4So7xeaKevNS+vNjaeYXMbIP8ylQB+gECgYEA/W2/FSbJ+uCiSN6+GGMATTih8jmYxx19JfwtzBnAuG+Scl/HkBWM5bedIlVu82tP/ckPthP9z1mNpS3zZnXTzU/tOq9EtGOXjHNi6zRKarvum2AD71ijgWtZaTvBmdtXkPLwMBpHCYVR43kRqabvvCg2MbL3p7kQI/c1OoDGtvECgYEAyHwd8MHtMJOoo7qDqHVfNOA7y1zkRhDuO8xbEdqK7SUckX1VdhprlDfWHTwAX0HNV+SYsu+YkLhIdeRlDS712kbKOr/R8OoXLQyUpqjt1+hV3kLsRQS2pgK0zqZjQMk0qwetUKEvLyvzPYBJASYX04hbwOjMZdv2gMSFJk3wdPUCgYEApJ7Eb+3pZ/DBGgquetLcYEz4TYsCZd0O7Ty5VwoneObovyjeTgbhplq2VJYgYziDFnyjmwezaBxo+TI3GIhTU9umYud0/qRuzgop6FToPhrjrMQa6j+uviPISaZKZTHo2LwmL2jyWgnjHpsHUSeiNJv+UBxL6QQ6qtIrHlyGOFECgYEAhOvdgfFhS/KqPZt8jOT8sXb1zfgRlO7GtLjhrG2j7GTNkxxw2/PaXZPDjvBoIr/i4CI8p245TcIQsLEz2lDHSefjTp49GChsIz8TE4gu4RY2UD04nu3oFxr2O4iPh7WfCMH3Q90KBpFyHNWAEZXq+CGRC86NLf9vaKJi0Smdgj0CgYA2uRvS7UVaDFl6BrHFLxQS7UJoUiHhO5zha+hm6wKRPD41rgsHPyx/stxzsL1jUQSIA3LG/N2jDSYsGuiIzYB0Fp3js43bXOlLyJs6A4VeXyV/cFuj9b31/+r9OZOSp7zrHTXPYnHnlFYMKLuy+D4b70dBldPaEB1ScWvaHp6KHw==-----END RSA PRIVATE KEY-----";
	//public static final String publicKeyString = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDGeJwU2g+qzdv8KMEssHoG9vGk+9PTeDp+kbAzqCijlqoW+BEvhHiZ9gNe4NnM7OEaR7rPVz67ELi4t/gRYAosiNYNb3E1fG8FYgwYIfGH8ve/Z6UmCqHsjkjvk+3BReWiTN4wlsI1dO7R8oxVoRqqcPQuLMexTp7jD1nCDDZGHKP/tX5dxglgNN/ysCuhDusin5fAsCUOV7f846UCOO0c71L1o99CVN1v2xFNdy8KSQQjYvfFDmJG4EEkLuRJuB0XlkCVnuq+wWiRc9dN8gBN3bPO+zsX8CaI65avy9MNP/LYgH15LgDbBh0xgnBFPYKUySxVGmK1S9pXT29IAkil";
	
	public static final String privateKeyString = "-----BEGIN RSA PRIVATE KEY-----\n"
			+ "MIIEowIBAAKCAQEAtP77/A5+GgPJu/llxRPMpVCZPXHXTunUwUhjhWLO2oLVuY2i\n"
			+ "gxhirQImT5Q0C6w5fIl0eckLALJ5K2VZLZHFEot5EccSZxaYOo+Ju8/y/nOCxgY2\n"
			+ "cGIailFxNKrfLkOfhQt+7+Xxa9DZYUinvE1lpTwIPqWB21EczrKxWq8mchgqMRGc\n"
			+ "a7/ivsW6APVBLRzw+GhrBhbsIcp+HHywn/OZhZ9tMfUsPv7QE/hYFWDydlS5E1V7\n"
			+ "qj/eXIBadRHBUS6+DWsTj/+8q+18tErDQD4D3KjeERImEfgd7QroisuxmE3EArmp\n"
			+ "eO8qK4HICpn1J24cRBqOJGPnp2QY6ShIIObi1QIDAQABAoIBAA2uYn9p3F1SC6Xc\n"
			+ "/bTZFh8piakhLvcPlMiWv1298MiH8D4qNxkKtwFrMq3RlDeM/qbBFcqo3Ynhc72f\n"
			+ "LQ+qlPIdvyU5Xio9r9G+t4NjU3OpaT6tCeC9LFK9Qj3gFqZUGpLqIgph4A8CxpO5\n"
			+ "yWK3RPzUSK6XlcptW1dCdEaDC82+5htdVWUb1UjpfgVnUgXezfBmPrWWptxNErp/\n"
			+ "L5E9IwfIfoyiEJxg6+JkNFqOihTPO09SOJZHEP584Vlnf4z2Y4B47nMTucOg0fBH\n"
			+ "eyPSyTr/NY/lYrp1cYiiizHkjRs7RWLh+IjwTMT8SacVxFikaj68pPXBVmd+/3je\n"
			+ "aUnUuwcCgYEAv9JPnp9e52Te6xQxOER73ffIEPfHkwlSgQf85ed+lgN+zUX3nU6b\n"
			+ "9/V05VjAN2DL0ky757Hpg3y3rNZuilNt4NszRdrNTF/O9n79oo2Rgsx9qR/jR3X3\n"
			+ "kzYS8eXefEGO5nebepstTgrnQAoGgeV16Ud48x5X9c9EGE8nX63zb78CgYEA8Y11\n"
			+ "s9e8a+QvF0LTNLH7HUIpJOogOs582D90uJXzuIUxPSs4XOsfOnHUZXoMIEIxiPqg\n"
			+ "68Cc4VgLCkMZb+7QLfdTor8Visfj6QkfGJ7eO2V3DHBjm7iqWnYgg1JDz4mX9rGS\n"
			+ "tfARcMd3WWVikTNf3FnRyv5q4nYvRBcYGCSkUmsCgYAhmp1aI39DuDcZJaNtwQmW\n"
			+ "HCdPWaj7yEFM23JhcEmLzDb1jRFQ3ImUScrdtsuNqAZs5AZzE4beAwZGYBZjYzQ3\n"
			+ "lQ8JaMhYXdxq8FpMlNJuAJbu/ugZB8CEaI+vEt8EE5QOKdvyAadRKbXTUS5MqT8b\n"
			+ "y+qTZLhyFopOW+eA6n9gDwKBgQDIsjQ9fN489pZ5yTga7FYsdTAiT7T/p1jrg63D\n"
			+ "QDQD+AZ1QFfQPDryIGnM9p7knprxqOvTdXHjeXeSyzVzXwTxbDG9Yu7PJE4k7UM8\n"
			+ "/aK/zwoe97KLS9+e0c8pzUwyP6xXeCrhp9mVzuVHDYncAsd/GdHJy6GbD38uH9IR\n"
			+ "HUC6iQKBgDiHN9vUiubftGfRYr0SiolkQNrsmrfVxvrpNaxDeZC4LP5sPEih3n3J\n"
			+ "OEOWedAdPri6ehkRmxWHHMV4hfDdGt2qx3uRwy2esUgFMQVMEvUD27Xl6b2Sh2/E\n"
			+ "SMRAkkzPIZ7Y1FCvEdG3YseFjHbbZepwMS7d6jVDmooOdxJ2vzFy\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	
	public static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtP77/A5+GgPJu/llxRPMpVCZPXHXTunUwUhjhWLO2oLVuY2igxhirQImT5Q0C6w5fIl0eckLALJ5K2VZLZHFEot5EccSZxaYOo+Ju8/y/nOCxgY2cGIailFxNKrfLkOfhQt+7+Xxa9DZYUinvE1lpTwIPqWB21EczrKxWq8mchgqMRGca7/ivsW6APVBLRzw+GhrBhbsIcp+HHywn/OZhZ9tMfUsPv7QE/hYFWDydlS5E1V7qj/eXIBadRHBUS6+DWsTj/+8q+18tErDQD4D3KjeERImEfgd7QroisuxmE3EArmpeO8qK4HICpn1J24cRBqOJGPnp2QY6ShIIObi1QIDAQAB";
	
	       // Generate token with given user name
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByEmail(userName)
        		.orElseThrow();
        claims.put("name", user.getFirstName());
        claims.put("email", user.getEmail());
        log.info("authorities({})",user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));
       claims.put("authorities",user.getRoles().stream().map(role -> role.getRoleName()).collect(Collectors.toList()));

        return createToken(claims, userName);
    }

    // Create a JWT token with specified claims and subject (user name)
    
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 90)) // Token valid for 90 minutes
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }
    
    
    // Get the private key for signing
    private PrivateKey getPrivateKey() {
//        try {
//        	
//        	// Remove headers, footers, and line breaks
//            String cleanedKey = privateKeyString
//                    .replaceAll("\\s", "");
//            
//            byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);
//            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return keyFactory.generatePrivate(keySpec);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid private key", e);
//        }
    	
//    	 try {
//    	        // Remove PEM headers and whitespace
//    	        String cleanedKey = privateKeyString
//    	                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
//    	                .replace("-----END RSA PRIVATE KEY-----", "")
//    	                .replaceAll("\\s", "");
//
//    	        // Decode Base64 content
//    	        byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);
//
//    	        // Parse the key using BouncyCastle's RSAPrivateKey
//    	        RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(keyBytes);
//
//    	        // Extract modulus and private exponent
//    	        BigInteger modulus = rsaPrivateKey.getModulus();
//    	        BigInteger privateExponent = rsaPrivateKey.getPrivateExponent();
//
//    	        // Create RSAPrivateKeySpec
//    	        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
//
//    	        // Generate PrivateKey
//    	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    	        return keyFactory.generatePrivate(keySpec);
//    	    } catch (Exception e) {
//    	        throw new IllegalArgumentException("Invalid PKCS#1 private key", e);
//    	    }
    	
    	
//    	 try {
//    		 
//    		 
//    	        // Use PEMParser to read the key
//    	        PEMParser pemParser = new PEMParser(new StringReader(privateKeyString));
//    	        Object parsedObject = pemParser.readObject();
//    	        pemParser.close();
//
//    	        // Parse as RSAPrivateKey or PrivateKeyInfo
//    	        RSAPrivateKey rsaPrivateKey;
//    	        if (parsedObject instanceof RSAPrivateKey) {
//    	            rsaPrivateKey = (RSAPrivateKey) parsedObject;
//    	        } else if (parsedObject instanceof PrivateKeyInfo) {
//    	            rsaPrivateKey = RSAPrivateKey.getInstance(((PrivateKeyInfo) parsedObject).parsePrivateKey());
//    	        } else {
//    	            throw new IllegalArgumentException("Invalid private key format");
//    	        }
//
//    	        // Extract modulus and private exponent
//    	        BigInteger modulus = rsaPrivateKey.getModulus();
//    	        BigInteger privateExponent = rsaPrivateKey.getPrivateExponent();
//
//    	        // Create RSAPrivateKeySpec
//    	        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
//
//    	        // Generate PrivateKey
//    	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//    	        return keyFactory.generatePrivate(keySpec);
//    	    } catch (Exception e) {
//    	        throw new IllegalArgumentException("Invalid PKCS#1 private key", e);
//    	    }
    	
    	
    	try {
            // Remove BEGIN/END lines and trim whitespace
            String cleanedKey = privateKeyString
                    .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                    .replace("-----END RSA PRIVATE KEY-----", "")
                    .replaceAll("\\s+", ""); // Removes all whitespace
            
            // Decode Base64 content
            byte[] keyBytes = java.util.Base64.getDecoder().decode(cleanedKey);
            
            // Parse the key using BouncyCastle ASN.1 structures
            RSAPrivateKey rsaPrivateKey = RSAPrivateKey.getInstance(keyBytes);
            
            // Extract key parameters (modulus and private exponent)
            BigInteger modulus = rsaPrivateKey.getModulus();
            BigInteger privateExponent = rsaPrivateKey.getPrivateExponent();
            
            // Create RSAPrivateKeySpec for the key
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
            
            // Generate and return the private key using KeyFactory
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid PKCS#1 private key format", e);
        }
    	
    	
    }
        
    
 // Get the public key for verification
    private PublicKey getPublicKey() {
//        try {
//            byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
//            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            return keyFactory.generatePublic(keySpec);
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid public key", e);
//        }
    	
//    	 try {
//             // Add the PEM header and footer for X.509 format
//             String pemKey = "-----BEGIN PUBLIC KEY-----\n" +
//                     Base64.getEncoder().encodeToString(Base64.getDecoder().decode(publicKeyString)) +
//                     "\n-----END PUBLIC KEY-----";
//
//             // Decode the PEM key to a byte array
//             byte[] keyBytes = Base64.getMimeDecoder().decode(pemKey.replaceAll("-----\\w+ PUBLIC KEY-----", "").trim());
//
//             // Create the public key from X.509EncodedKeySpec
//             X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//             KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//             return keyFactory.generatePublic(keySpec);
//
//         } catch (Exception e) {
//             throw new IllegalArgumentException("Invalid public key", e);
//         }
//    	
    	
//    	 try {
//             String pemKey = "ssh-rsa " + publicKeyString;
//             PEMParser pemParser = new PEMParser(new StringReader(pemKey));
//             PemObject pemObject = pemParser.readPemObject();
//
//             // Extract the public key from the PemObject
//             byte[] keyBytes = pemObject.getContent();
//
//             // Convert to X.509 public key format
//             X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//             KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//             return keyFactory.generatePublic(keySpec);
//         } catch (Exception e) {
//             throw new IllegalArgumentException("Invalid public key", e);
//         }
    	
//    	 try {
//             String pemFormattedKey = wrapInPemFormat(publicKeyString);  // Wrap the key in PEM format
//             PEMParser pemParser = new PEMParser(new StringReader(pemFormattedKey));
//
//             PemObject pemObject = pemParser.readPemObject();
//             if (pemObject == null) {
//                 throw new IllegalArgumentException("Invalid public key format");
//             }
//
//             // Extract the public key from the PemObject
//             byte[] keyBytes = pemObject.getContent();
//
//             // Convert to X.509 public key format
//             X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//             KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//             return keyFactory.generatePublic(keySpec);
//         } catch (Exception e) {
//             throw new IllegalArgumentException("Invalid public key", e);
//         }
    	
    	
    	
    	
    	try {
    		
    		
    		   
    		
    		
            // Clean up the PEM format string by removing the header and footer
            String cleanedKey = publicKeyString
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");  // Remove all whitespace

            // Decode the Base64 encoded key
            byte[] keyBytes = Base64.getDecoder().decode(cleanedKey);

            // Use X509EncodedKeySpec to convert the byte array to an RSA PublicKey
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
        	throw new IllegalArgumentException("Invalid public key", e); 
        }
     	
    }
    
    private static String wrapInPemFormat(String key) {
        return "-----BEGIN PUBLIC KEY-----\n" + 
                key.replaceAll("(.{64})", "$1\n") +  // Adds newlines every 64 characters
                "\n-----END PUBLIC KEY-----";
    }
    
    

    // Get the signing key for JWT token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
 // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    // Generate RSA key pair with a given key size
    public static KeyPair generateRSAKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }
    
}

