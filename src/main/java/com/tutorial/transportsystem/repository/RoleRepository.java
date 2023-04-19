package com.tutorial.transportsystem.repository;

import com.tutorial.transportsystem.entity.ERole;
import com.tutorial.transportsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(ERole name);
}
