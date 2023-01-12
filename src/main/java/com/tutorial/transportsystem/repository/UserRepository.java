package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserDto, Long> {

    //TODO Optional?
    User findByUsername(String username); // User findByLogin(String username);

    Boolean existsByUsername(String username);

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    Optional<UserDto> findById();
}
