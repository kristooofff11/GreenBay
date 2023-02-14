package com.example.greenbay.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.greenbay.exeptions.UnauthorizedException;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader =
        request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String token = authorizationHeader.substring("Bearer ".length());

      Dotenv dotenv = Dotenv.load();

      Algorithm algorithm = Algorithm.HMAC256(
          Objects.requireNonNull(dotenv.get("JWT_SECRET_KEY")).getBytes(StandardCharsets.UTF_8));

      JWTVerifier verifier = JWT.require(algorithm)
          .build();

      DecodedJWT decodedJWT =
          verifier.verify(token);

      String username = decodedJWT.getSubject();

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(username,null);

      SecurityContextHolder.getContext()
          .setAuthentication(authenticationToken);

      filterChain.doFilter(request,response);
    }else {
      throw new UnauthorizedException("Access denied");
    }
  }
}
