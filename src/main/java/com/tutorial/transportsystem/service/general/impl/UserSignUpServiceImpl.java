package com.tutorial.transportsystem.service.general.impl;

import com.tutorial.transportsystem.entity.ERole;
import com.tutorial.transportsystem.entity.Role;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.payload.request.SignupRequest;
import com.tutorial.transportsystem.payload.responce.MessageResponse;
import com.tutorial.transportsystem.repository.RoleRepository;
import com.tutorial.transportsystem.repository.UserRepository;
import com.tutorial.transportsystem.service.general.UserSignUpService;
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

        if (userRepository.existsByLogin(rq.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(rq.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user =
                new User()
                        .setLogin(rq.getUsername())
                        .setEmail(rq.getEmail())
                        .setPassword(encoder.encode((rq.getPassword())));

        List<ERole> strRoles = rq.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole =
                    roleRepository
                            .findByName(ERole.USER)
                            .orElseThrow(() -> new RuntimeException("Error: " + strRoles + " is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(
                    role -> {
                        Role roleEntity =
                                roleRepository
                                        .findByName(role)
                                        .orElseThrow(() -> new RuntimeException("Error: " + role + " is not found."));
                        roles.add(roleEntity);
                    });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return new MessageResponse("User registered successfully!");
    }
}
