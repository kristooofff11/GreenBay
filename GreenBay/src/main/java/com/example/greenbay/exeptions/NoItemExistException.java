package com.example.greenbay.exeptions;

public class NoItemExistException extends RuntimeException{
  private final String parameter;

  public NoItemExistException(String parameter,String message) {
    super(message);
    this.parameter = parameter;
  }
}
