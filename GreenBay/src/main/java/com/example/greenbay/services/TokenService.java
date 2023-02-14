package com.example.greenbay.services;

import com.example.greenbay.models.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

  AppUser getUserFromAuthorizationHeader(String bearerToken);

  String getToken(UserDetails userDetails);

  UserDetails loadUserByUsername(String username);

}
