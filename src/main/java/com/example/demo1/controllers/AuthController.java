package com.example.demo1.controllers;


import java.io.StringWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.asn1.pkcs.RSAPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.dtos.LoginDto;
import com.example.demo1.dtos.SignUpDto;
import com.example.demo1.entities.Role;
import com.example.demo1.entities.User;
import com.example.demo1.repositories.RoleRepository;
import com.example.demo1.repositories.UserRepository;
import com.example.demo1.services.jwtTokenService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private jwtTokenService jwttokenService;
    
    public static final String ADMIN = "ROLE_ADMIN";
    
    public static final String DRIVER = "ROLE_DRIVER";
    
    @PostMapping("/signin")
    public ResponseEntity<Map<String,String>> authenticateUser(@RequestBody LoginDto loginDto){
//    	 try
//         {
//          KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//          keyPairGenerator.initialize(2048);  // Use 2048-bit key size
//          KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//          // Get the private key
//          RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//          // Convert private key to PKCS#1 format (PEM encoded)
//          String privateKeyPem = "-----BEGIN RSA PRIVATE KEY-----\n" +
//                  java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded()) +
//                  "\n-----END RSA PRIVATE KEY-----";
//
//         log.info("private({})",privateKeyPem);
//
//          // Get the public key
//          RSAPublicKey publicKey1 = (RSAPublicKey) keyPair.getPublic();
//
//          // Convert public key to SSH RSA format (with "ssh-rsa" prefix)
//          String publicKeyPem = "ssh-rsa " +
//                  java.util.Base64.getEncoder().encodeToString(publicKey1.getEncoded()) +
//                  " user@host";  // Add an identifier at the end (e.g., user@host)
//
//          log.info("public({})",publicKey1);
//
//          System.out.println("Private key and public key generated and saved.");
//         }
//         catch(Exception e)
//         {
//      	   log.info("error({})",e);
//         }
    	
    	
    	 try {
             // Add BouncyCastle Provider
             Security.addProvider(new BouncyCastleProvider());

             // Generate RSA key pair
             KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
             keyPairGenerator.initialize(2048);  // 2048-bit key size
             KeyPair keyPair = keyPairGenerator.generateKeyPair();

             // Get the private key and convert to PKCS#1 format (PEM encoded)
             PrivateKey privateKey = keyPair.getPrivate();
             String privateKeyPem = convertPrivateKeyToPem(privateKey);

             log.info("Private Key: \n" + privateKeyPem);

             // Get the public key and convert to SSH-RSA format
             PublicKey publicKey = keyPair.getPublic();
             String publicKeyPem = convertPublicKeyToSshRsa(publicKey);

             log.info("Public Key: \n" + publicKeyPem);

             System.out.println("Private key and public key generated and saved.");

         } catch (Exception e) {
             log.error("Error generating RSA keys: ", e);
         }
    	
    	
    	
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        log.info("auth({})",authentication);
        // Get the principal (user details object)
        Object principal = authentication.getPrincipal();
        String username=null;
        // Check if the principal is an instance of User
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            // Cast to User and get the username
            username = ((org.springframework.security.core.userdetails.User) principal).getUsername();

            // Log the username
            log.info("email({})", username);
        } else {
            log.info("Principal is not an instance of User");
        }
        
        String token = jwttokenService.generateToken(username);
        log.info("token({})",token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
      
        
        return new ResponseEntity<>(response, HttpStatus.OK);
//        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
    
    
 // Convert private key to PEM (PKCS#1 format)
    public static String convertPrivateKeyToPem(PrivateKey privateKey) throws Exception {
        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
        pemWriter.writeObject(privateKey);
        pemWriter.close();
        return stringWriter.toString();
    }

    // Convert public key to SSH-RSA format
    public static String convertPublicKeyToSshRsa(PublicKey publicKey) throws Exception {
        byte[] encoded = publicKey.getEncoded();
        String base64PublicKey = java.util.Base64.getEncoder().encodeToString(encoded);
        return "ssh-rsa " + base64PublicKey + " user@host";  // You can replace "user@host" with your identifier
    }

    

    @PostMapping("/signup")
   // @PreAuthorize("permitAll()")
  //  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  //  @PreAuthorize("isAuthenticated")
    @Secured(ADMIN)
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	log.info("authentication({})",authentication);
        // add check for username exists in a DB
        if(userRepository.existsByFirstName(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFirstName(signUpDto.getName());
        user.setLastName(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByRoleName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
