package com.bank.service.bankservice.dto.response;

import com.bank.service.bankservice.model.Role;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String dateOfBirth;

    private String email;

    private String phoneNumber;

    private String password;

    private Set<Role> roles;
}
