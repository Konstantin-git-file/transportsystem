package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByLastnameContainingIgnoreCase(String lastname, Pageable pageable);
    boolean existsByLogin(String login);
    Optional<User> findByLogin(String login);
}