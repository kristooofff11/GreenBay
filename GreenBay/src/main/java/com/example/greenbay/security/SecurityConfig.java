package com.example.greenbay.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public static final int TOKEN_EXPIRATION_TIME = 3600000;

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web -> web.ignoring().antMatchers("/login","/signup"));
  }
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();
    httpSecurity.addFilterBefore(
        new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

    httpSecurity.csrf().disable();

    httpSecurity
        .headers()
        .xssProtection()
        .and()
        .contentSecurityPolicy("script-src 'self'; form-action 'self'")
        .and()
        .referrerPolicy(ReferrerPolicy.SAME_ORIGIN)
        .and()
        .permissionsPolicy(permissionsPolicyConfig -> permissionsPolicyConfig.policy("geolocation=(self)"));
    return httpSecurity.build();
  }
}
