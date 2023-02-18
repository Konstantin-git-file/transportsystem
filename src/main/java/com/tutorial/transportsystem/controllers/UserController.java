package com.tutorial.transportsystem.controllers;


import com.tutorial.transportsystem.dto.UserDto;
import com.tutorial.transportsystem.service.impl.FindAllUserDtosUserServiceImpl;
import com.tutorial.transportsystem.service.impl.GetUserByIdServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final FindAllUserDtosUserServiceImpl findAllUserDtosUserService;
    private final GetUserByIdServiceImpl getUserByIdService;


    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(@RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
                                     @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {

        return findAllUserDtosUserService.findAllUserDtos(limit, offset);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        return getUserByIdService.getUserById(id);
    }
}