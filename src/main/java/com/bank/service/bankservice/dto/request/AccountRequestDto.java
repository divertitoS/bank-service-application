package com.bank.service.bankservice.dto.request;

import com.bank.service.bankservice.model.Currency;
import com.bank.service.bankservice.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequestDto {

    private String accountNumber;

    private Currency currency;

    private BigDecimal balance;

    private User user;
}
