package com.bank.service.bankservice.dto.response;

import com.bank.service.bankservice.model.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;
}
