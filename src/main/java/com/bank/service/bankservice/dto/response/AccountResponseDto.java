package com.bank.service.bankservice.dto.response;

import com.bank.service.bankservice.model.Currency;
import com.bank.service.bankservice.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponseDto {

    private Long id;

    private String accountNumber;

    private Currency currency;

    private BigDecimal balance;

    private boolean isActive;

    private User user;
}