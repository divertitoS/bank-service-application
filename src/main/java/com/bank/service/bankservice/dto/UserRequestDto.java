package com.bank.service.bankservice.dto;

import com.bank.service.bankservice.model.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserRequestDto {

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;
}
