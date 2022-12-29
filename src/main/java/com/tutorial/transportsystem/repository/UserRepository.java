package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}