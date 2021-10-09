package com.bank.service.bankservice.controller;

import com.bank.service.bankservice.dto.request.UserRequestDto;
import com.bank.service.bankservice.dto.response.UserResponseDto;
import com.bank.service.bankservice.model.Role;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.service.RoleService;
import com.bank.service.bankservice.service.UserService;
import com.bank.service.bankservice.service.mapper.UserMapper;
import com.bank.service.bankservice.util.DateTimePatternUtil;
import java.util.Set;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    public static final String DATE_PATTERN = DateTimePatternUtil.DATE_PATTERN;

    private final UserService userService;

    private final RoleService roleService;

    private final UserMapper userMapper;

    public UserController(UserService userService, RoleService roleService, UserMapper userMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        User user = userMapper.mapToModel(dto);
        user.setRoles(Set.of(roleService.getByName(Role.RoleName.USER)));
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
