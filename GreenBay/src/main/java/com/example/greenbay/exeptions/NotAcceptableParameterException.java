package com.example.greenbay.exeptions;

public class NotAcceptableParameterException extends RuntimeException{

  private final String parameter;

  public NotAcceptableParameterException(String parameter,String message) {
    super(message);
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }
}
