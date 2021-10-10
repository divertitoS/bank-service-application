package com.bank.service.bankservice.controller;

import com.bank.service.bankservice.dto.request.UserRequestDto;
import com.bank.service.bankservice.dto.response.UserResponseDto;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.security.AuthenticationService;
import com.bank.service.bankservice.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    private final UserMapper userMapper;

    public AuthenticationController(AuthenticationService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto dto) {
        User user = authService.register(dto.getEmail(), dto.getPassword());
        return userMapper.mapToDto(user);
    }
}
