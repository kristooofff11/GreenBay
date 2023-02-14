package com.example.greenbay.services;

import static com.example.greenbay.security.SecurityConfig.TOKEN_EXPIRATION_TIME;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.greenbay.models.entities.AppUser;
import com.example.greenbay.repositories.AppUserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class TokenServiceImpl implements TokenService{

  private final AppUserRepository appUserRepository;

  public TokenServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public AppUser getUserFromAuthorizationHeader(String bearerToken) {
    Dotenv dotenv = Dotenv.load();
    String token = bearerToken.substring("bearer ".length());

    Algorithm algorithm = Algorithm.HMAC256(
        Objects.requireNonNull(dotenv.get("JWT_SECRET_KEY")).getBytes(StandardCharsets.UTF_8));

    JWTVerifier verifier = JWT.require(algorithm).build();

    DecodedJWT decodedJWT = verifier.verify(token);

    String username = decodedJWT.getSubject();
    return appUserRepository.findAppUserByUsername(username);
  }

  @Override
  public String getToken(UserDetails userDetails) {
    Dotenv dotenv = Dotenv.load();
    Algorithm algorithm =
        Algorithm.HMAC256(
            Objects.requireNonNull(dotenv.get("JWT_SECRET_KEY"))
                .getBytes(StandardCharsets.UTF_8));
    return JWT.create()
        .withSubject(userDetails.getUsername())
        .withExpiresAt(
            new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
        .withIssuer(ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/login")
            .toString())
        .sign(algorithm);
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    AppUser appUser = appUserRepository.findAppUserByUsername(username);
    if(appUser == null) {
      throw new UsernameNotFoundException("User not found in the database.");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    return new User(appUser.getUsername(),appUser.getPassword(),authorities);
  }
}
