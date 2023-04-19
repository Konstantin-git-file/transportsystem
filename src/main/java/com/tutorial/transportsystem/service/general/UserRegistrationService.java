package com.tutorial.transportsystem.service.general;

import com.tutorial.transportsystem.dto.userRegistrationDto.UserRegistrationDtoRq;
import com.tutorial.transportsystem.dto.userRegistrationDto.UserRegistrationDtoRs;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

  private final UserMapper userMapper;

  private final UserRepository userRepository;

  public UserRegistrationDtoRs makeRegistrationUser(UserRegistrationDtoRq rq) {
//    if (userRepository.existsByLogin(rq.getLogin())) {
//      throw new TransportSystemException(USER_LOGIN_ALREADY_EXISTS);
//    }
//    if (userRepository.existsByEmail(rq.getEmail())) {
//      throw new TransportSystemException(USER_EMAIL_ALREADY_EXISTS);
//    }
//
//    Passport passport =
//        new Passport()
//            .setSerial(rq.getPassport().getSerial())
//            .setNumber(rq.getPassport().getNumber())
//            .setGender(rq.getPassport().getGender());
//    User user =
//        new User()
//            .setLogin(rq.getLogin())
//            .setEmail(rq.getEmail())
//            .setFirstname(rq.getFirstname())
//            .setLastname(rq.getLastname())
//            .setPassport(passport);
//
//    user = userRepository.save(user);
//
//    return new UserRegistrationDtoRs().setUserId(user.getId());
    return null;
  }
}
