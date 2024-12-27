package com.example.demo1.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot
    implements MethodSecurityExpressionOperations {

  private Object filterObject;
  private Object returnObject;

  public CustomMethodSecurityExpressionRoot(Authentication authentication) {
    super(authentication);
  }

  @Override
  public void setFilterObject(Object filterObject) {
    this.filterObject = filterObject;
  }

  @Override
  public Object getFilterObject() {
    return this.filterObject;
  }

  @Override
  public void setReturnObject(Object returnObject) {
    this.returnObject = returnObject;
  }

  @Override
  public Object getReturnObject() {
    return this.returnObject;
  }

  @Override
  public Object getThis() {
    return this;
  }

//  public boolean hasAnyClientRole(String clientId, String... roles) {
//    return AuthDetailsProvider.hasAnyClientRole(this.getAuthentication(), clientId, roles);
//  }

}

