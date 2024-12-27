package com.example.demo1.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Error {

  private ErrorCode code;
  private Object[] parameters;

  public Error(ErrorCode code, Object... parameters) {
    this.code = code;
    this.parameters = parameters;
  }

}
