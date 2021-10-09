package com.bank.service.bankservice.dto.request;

import com.bank.service.bankservice.model.Role;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class UserRequestDto {

    private String name;

    private LocalDate dateOfBirth;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;
}
