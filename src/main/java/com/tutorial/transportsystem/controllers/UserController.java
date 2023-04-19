package com.tutorial.transportsystem.controllers;

import com.tutorial.transportsystem.dto.generalDto.UserDto;
import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRq;
import com.tutorial.transportsystem.dto.userBookingDto.UserBookTicketDtoRs;
import com.tutorial.transportsystem.payload.responce.UserCancelTicketServiceRs;
import com.tutorial.transportsystem.repository.TrainRepository;
import com.tutorial.transportsystem.service.booking.Impl.UserBookTicketServiceImpl;
import com.tutorial.transportsystem.service.booking.Impl.UserCancelTicketServiceImpl;
import com.tutorial.transportsystem.service.general.impl.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FindAllUserDtosUserServiceImpl findAllUserDtosUserService;
    private final GetUserByIdServiceImpl getUserByIdService;
    private final UpdateUserDtoImpl updateUserDtoImpl;
    private final DeleteUserDtoUserServiceImpl deleteUserDtoUserServiceImpl;
    private final UserBookTicketServiceImpl userBookTicketService;
    private final UserCancelTicketServiceImpl userCancelTicketService;
    private final TrainServiceImpl trainService;
    private final TrainRepository trainRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(
            @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return findAllUserDtosUserService.findAllUserDtos(limit, offset);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    @Transactional
    public UserDto getUserById(@PathVariable("id") long id) {
        return getUserByIdService.getUserById(id);
    }

    @PreAuthorize("hasRole('USER'or'ADMIN')")
    @PatchMapping("/update/{id}")
    public UserDto updateUserById(@PathVariable("id") long id, @RequestBody UserDto userdto) {
        return updateUserDtoImpl.updateUserDto(id, userdto);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/buy_ticket")
    public UserBookTicketDtoRs bookTicket(@RequestBody @Valid UserBookTicketDtoRq rq) {
        return userBookTicketService.bookTicket(rq);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/cancel_ticket")
    public UserCancelTicketServiceRs bookTicket(@RequestBody @Valid Long ticketId) {
        return userCancelTicketService.cancelTicket(ticketId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") long id) {
        deleteUserDtoUserServiceImpl.deleteUserDto(id);
    }

}
