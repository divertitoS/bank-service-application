package com.bank.service.bankservice.dto.request;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionRequestDto {

    private Account accountFrom;

    private Account accountTo;

    private BigDecimal amount;
}
