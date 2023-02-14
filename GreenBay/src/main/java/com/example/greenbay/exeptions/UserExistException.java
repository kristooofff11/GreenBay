package com.example.greenbay.exeptions;

public class UserExistException extends RuntimeException{

  public UserExistException(String message) {
    super(message);
  }
}
