package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);

  boolean existsByLogin(String login);

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  User findByPassportPassportID (Long passportID);

}
