package com.example.demo1.exception;

import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

/**
 * @author Basit Azeem Sheikh
 * 
 */
@ToString
public class ServiceException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -5652182259350619880L;

  private final List<Error> errors;

  // For Exceptional cases
  private String overrideMessage;
  private List<ErrorCode> detailedCodes;

  public ServiceException(ErrorCode code, Object... messageParameters) {
    this.errors = new ArrayList<>();
    this.errors.add(new Error(code, messageParameters));
  }

  public ServiceException(List<Error> errors) {
    this.errors = errors;
  }

  public List<Error> getErrors() {
    return errors;
  }

  public String getOverrideMessage() {
    return overrideMessage;
  }

  public void setOverrideMessage(String overrideMessage) {
    this.overrideMessage = overrideMessage;
  }

  public List<ErrorCode> getDetailedCodes() {
    return detailedCodes;
  }

  public void setDetailedCodes(List<ErrorCode> detailedCodes) {
    this.detailedCodes = detailedCodes;
  }

}
