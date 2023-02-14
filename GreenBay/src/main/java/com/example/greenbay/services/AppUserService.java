package com.example.greenbay.services;

import com.example.greenbay.models.entities.AppUser;
import java.util.List;

public interface AppUserService {

  List<AppUser> getAllUser();

  AppUser saveUser(AppUser appUser);
  AppUser saveNewUser(AppUser appUser);

  boolean userExist(String username);

  AppUser findUserByUsername(String username);

  boolean passwordIsCorrect(String rawPassword,String encodedPassword);

}
