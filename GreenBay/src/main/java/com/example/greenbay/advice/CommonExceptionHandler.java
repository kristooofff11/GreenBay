package com.example.greenbay.advice;

import static com.example.greenbay.configuration.ExceptionMessageConfiguration.MISSING_PARAMETER_ERROR;
import static com.example.greenbay.configuration.ExceptionMessageConfiguration.NOT_ACCEPTABLE_PARAMETER_ERROR;
import static com.example.greenbay.configuration.ExceptionMessageConfiguration.NO_ITEM_EXIST_ERROR;
import static com.example.greenbay.configuration.ExceptionMessageConfiguration.NO_USER_EXIST_ERROR;
import static com.example.greenbay.configuration.ExceptionMessageConfiguration.UNAUTHORIZED_ERROR;
import static com.example.greenbay.configuration.ExceptionMessageConfiguration.USER_EXIST_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import com.example.greenbay.exeptions.MissingParameterException;
import com.example.greenbay.exeptions.NoItemExistException;
import com.example.greenbay.exeptions.NoUserExistException;
import com.example.greenbay.exeptions.NotAcceptableParameterException;
import com.example.greenbay.exeptions.UnauthorizedException;
import com.example.greenbay.exeptions.UserExistException;
import com.example.greenbay.models.dto.ErrorResponseDto;
import java.time.ZonedDateTime;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

  @ExceptionHandler(UserExistException.class)
  @ResponseStatus(value = CONFLICT)
  public ErrorResponseDto handleUserExistException(UserExistException e) {
    return new ErrorResponseDto(
        USER_EXIST_ERROR,
        CONFLICT,
        ZonedDateTime.now(),
        e.getMessage());
  }
  @ExceptionHandler(MissingParameterException.class)
  @ResponseStatus(value = BAD_REQUEST)
  public ErrorResponseDto handleMissingParameterException(MissingParameterException e) {
    return new ErrorResponseDto(
        MISSING_PARAMETER_ERROR + e.getParameter(),
        BAD_REQUEST,
        ZonedDateTime.now(),
        e.getMessage());
  }
  @ExceptionHandler(NotAcceptableParameterException.class)
  @ResponseStatus(value = NOT_ACCEPTABLE)
  public ErrorResponseDto handleNotAcceptableParameter(NotAcceptableParameterException e) {
    return new ErrorResponseDto(
        NOT_ACCEPTABLE_PARAMETER_ERROR + e.getParameter(),
        NOT_ACCEPTABLE,
        ZonedDateTime.now(),
        e.getMessage());
  }
  @ExceptionHandler(NoUserExistException.class)
  @ResponseStatus(value = CONFLICT)
  public ErrorResponseDto handleNoUserExistException(NoUserExistException e){
    return new ErrorResponseDto(
        NO_USER_EXIST_ERROR,
        CONFLICT,
        ZonedDateTime.now(),
        e.getParameter());
  }
  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(value = UNAUTHORIZED)
  public ErrorResponseDto handleUnautorizedException(UnauthorizedException e){
    return new ErrorResponseDto(
        UNAUTHORIZED_ERROR,
        UNAUTHORIZED,
        ZonedDateTime.now(),
        e.getMessage());
  }
  @ExceptionHandler(NoItemExistException.class)
  @ResponseStatus(value = CONFLICT)
  public ErrorResponseDto handleNoItemExistException(NoItemExistException e){
    return new ErrorResponseDto(
        NO_ITEM_EXIST_ERROR,
        CONFLICT,ZonedDateTime.now(),
        e.getMessage());
  }
}
