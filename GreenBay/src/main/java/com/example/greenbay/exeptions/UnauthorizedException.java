package com.example.greenbay.exeptions;

public class UnauthorizedException extends RuntimeException{

  public UnauthorizedException(String message){
    super(message);
  }

}
