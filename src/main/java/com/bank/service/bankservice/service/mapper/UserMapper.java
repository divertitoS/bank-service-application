package com.bank.service.bankservice.service.mapper;

import com.bank.service.bankservice.dto.request.UserRequestDto;
import com.bank.service.bankservice.dto.response.UserResponseDto;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.util.DateTimePatternUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements RequestDtoMapper<UserRequestDto, User>,
        ResponseDtoMapper<UserResponseDto, User> {

    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(DateTimePatternUtil.DATE_PATTERN);

    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth().toString(), formatter));
        user.setRoles(dto.getRoles());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(model.getId());
        dto.setEmail(model.getEmail());
        dto.setName(dto.getName());
        dto.setPassword(model.getPassword());
        dto.setRoles(model.getRoles());
        dto.setDateOfBirth(model.getDateOfBirth().format(formatter));
        dto.setPhoneNumber(model.getPhoneNumber());
        return dto;
    }
}
