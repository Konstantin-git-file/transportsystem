package com.tutorial.transportsystem.service.impl;

import com.tutorial.transportsystem.entity.ERole;
import com.tutorial.transportsystem.entity.Role;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.payload.request.SignupRequest;
import com.tutorial.transportsystem.payload.responce.MessageResponse;
import com.tutorial.transportsystem.repository.RoleRepository;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.UserSignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserSignUpServiceImpl implements UserSignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public MessageResponse signUp(SignupRequest rq) {
        if (Boolean.TRUE.equals(userRepository.existsByLogin(rq.getUsername()))) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(rq.getEmail()))) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User()
                .setLogin(rq.getUsername())
                .setEmail(rq.getEmail())
                .setPassword(encoder.encode((rq.getPassword())));

        List<ERole> strRoles = rq.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: " + strRoles + " is not found."));
            roles.add(userRole);
        } else {
            // TODO Enum delete switch
            strRoles.forEach(role -> {
                switch (role) {
                    case ROLE_ADMIN -> roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: " + role + " is not found."));
                    case ROLE_MODERATOR -> roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: " + role + " is not found."));
                    default -> throw new RuntimeException("Unexpected role: " + role);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }
}
