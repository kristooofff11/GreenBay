package com.example.greenbay.repositories;

import com.example.greenbay.models.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
  AppUser findAppUserByUsername(String username);
}
