package com.example.greenbay.exeptions;

public class MissingParameterException extends RuntimeException{

  public  final String parameter;

  public MissingParameterException(String parameter,String message) {
    super(message);
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }
}
