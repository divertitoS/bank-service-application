package com.bank.service.bankservice.controller;

import com.bank.service.bankservice.dto.UserRequestDto;
import com.bank.service.bankservice.dto.UserResponseDto;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.service.UserService;
import com.bank.service.bankservice.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        User user = userMapper.mapToModel(dto);
        User savedUser = userService.save(user);
        return userMapper.mapToDto(savedUser);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto dto) {
        User user = userService.getUserInfo(id);

        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setRoles(dto.getRoles());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setDateOfBirth(dto.getDateOfBirth());

        User updatedUser = userService.save(user);
        return userMapper.mapToDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserResponseDto getInfo(@PathVariable Long id) {
        User userInfo = userService.getUserInfo(id);
        return userMapper.mapToDto(userInfo);
    }

    @GetMapping("/by-phone")
    public UserResponseDto findByPhoneNumber(@RequestParam String phoneNumber) {
        User user = userService.findByPhoneNumber(phoneNumber);
        return userMapper.mapToDto(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
