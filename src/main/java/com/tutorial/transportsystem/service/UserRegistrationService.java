package com.tutorial.transportsystem.service;

import com.tutorial.transportsystem.dto.user.UserRegistrationDtoRq;
import com.tutorial.transportsystem.dto.user.UserRegistrationDtoRs;
import com.tutorial.transportsystem.entity.Passport;
import com.tutorial.transportsystem.entity.User;
import com.tutorial.transportsystem.exception.TransportSystemException;
import com.tutorial.transportsystem.mapper.PassportMapper;
import com.tutorial.transportsystem.mapper.UserMapper;
import com.tutorial.transportsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tutorial.transportsystem.exception.ErrorEnum.USER_EMAIL_ALREADY_EXISTS;
import static com.tutorial.transportsystem.exception.ErrorEnum.USER_LOGIN_ALREADY_EXISTS;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

  private UserMapper userMapper;
  private PassportMapper passportMapper;
  private UserRepository userRepository;

  public UserRegistrationDtoRs makeRegistrationUser(UserRegistrationDtoRq rq) {
    if (userRepository.existsByLogin(rq.getLogin())) {
      throw new TransportSystemException(USER_LOGIN_ALREADY_EXISTS);
    }
    if (userRepository.existsByEmail(rq.getEmail())) {
      throw new TransportSystemException(USER_EMAIL_ALREADY_EXISTS);
    }

    Passport passport =
        new Passport()
            .setSerial(rq.getPassport().getSerial())
            .setNumber(rq.getPassport().getNumber())
            .setGender(rq.getPassport().getGender());
    User user =
        new User()
            .setLogin(rq.getLogin())
            .setEmail(rq.getEmail())
            .setFirstname(rq.getFirstname())
            .setLastname(rq.getLastname())
            .setPassport(passport);

    user = userRepository.save(user);

    return new UserRegistrationDtoRs().setUserId(user.getId());
  }
}
