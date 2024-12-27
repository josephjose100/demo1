package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import com.example.demo1.security.CustomMethodSecurityExpressionHandler;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class MethodSecurityConfig {

//  @Override
//  protected MethodSecurityExpressionHandler createExpressionHandler() {
//    return new CustomMethodSecurityExpressionHandler();
//  }

  @Bean
  public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
    return new CustomMethodSecurityExpressionHandler();
  }
}