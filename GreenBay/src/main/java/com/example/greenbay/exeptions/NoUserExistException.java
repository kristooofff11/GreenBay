package com.example.greenbay.exeptions;

public class NoUserExistException extends RuntimeException{

  private final String parameter;

  public NoUserExistException(String parameter) {
    this.parameter = parameter;
  }

  public String getParameter() {
    return parameter;
  }
}
