package com.example.greenbay.controllers;

import com.example.greenbay.exeptions.MissingParameterException;
import com.example.greenbay.exeptions.NoUserExistException;
import com.example.greenbay.exeptions.NotAcceptableParameterException;
import com.example.greenbay.exeptions.UserExistException;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.models.dto.UserLoginDto;
import com.example.greenbay.services.AppUserService;
import com.example.greenbay.services.TokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final AppUserService appUserService;

  private final TokenServiceImpl tokenService;

  public UserController(AppUserService appUserService, TokenServiceImpl tokenService) {
    this.appUserService = appUserService;
    this.tokenService = tokenService;
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signUpAppUser(@RequestBody(required = false) AppUser appUser){
    if (appUser == null  || appUser.getUsername().equals("") && appUser.getPassword().equals("")) {
      throw new MissingParameterException("Username and password is required!","/signup");
    }
    if(appUser.getUsername() == null || appUser.getUsername().equals("")){
      throw new MissingParameterException("Username is required!","/signup");
    }
    if(appUser.getPassword() == null || appUser.getPassword().equals("")){
      throw new MissingParameterException("Password is required!","/signup");
    }
    if(appUser.getPassword().length() < 8){
      throw new NotAcceptableParameterException("Password must be  8 characters!","/signup");
    }
    if (appUserService.userExist(appUser.getUsername())){
      throw new UserExistException("/signup");
    }else {
      return ResponseEntity.ok().body(appUserService.saveNewUser(appUser));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody(required = false) UserLoginDto userLoginDto){
    if (userLoginDto == null
        || userLoginDto.getPassword().equals("") && userLoginDto.getUsername().equals("")) {
      throw new MissingParameterException("username, password","/login" );
    }
    if (userLoginDto.getPassword().equals("")) {
      throw new MissingParameterException("password", "/login");
    }
    if (userLoginDto.getUsername().equals("")) {
      throw new MissingParameterException("username", "/login");
    }
    if (appUserService.findUserByUsername(userLoginDto.getUsername()) == null) {
      throw new NoUserExistException("/login");
    }
    org.springframework.security.core.userdetails.User loggingUser =
        (org.springframework.security.core.userdetails.User)
            tokenService.loadUserByUsername(
                userLoginDto.getUsername());
    return ResponseEntity.status(200)
        .body(tokenService.getToken(loggingUser));
  }
  @GetMapping("/getAllUser")
  public ResponseEntity<?> getAllUser(){
    return ResponseEntity.ok().body(appUserService.getAllUser());
  }
}
