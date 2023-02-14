package com.example.greenbay.services;

import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.repositories.AppUserRepository;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService{

  private final AppUserRepository appUserRepository;

  private final PasswordEncoder passwordEncoder;

  public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
    this.appUserRepository = appUserRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<AppUser> getAllUser() {
    return appUserRepository.findAll();
  }

  @Override
  public AppUser saveUser(AppUser appUser) {
    return appUserRepository.save(appUser);
  }

  @Override
  public AppUser saveNewUser(AppUser appUser) {
    AppUser newAppUser = new AppUser(appUser.getUsername(),passwordEncoder.encode(appUser.getPassword()));
    return appUserRepository.save(newAppUser);
  }

  @Override
  public boolean userExist(String username) {
    return appUserRepository.findAppUserByUsername(username) != null;
  }

  @Override
  public AppUser findUserByUsername(String username) {
    return appUserRepository.findAppUserByUsername(username);
  }

  @Override
  public boolean passwordIsCorrect(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword,encodedPassword);
  }

}
