package com.bank.service.bankservice.dto.request;

import com.bank.service.bankservice.model.Account;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionRequestDto {

    private Account accountFrom;

    private Account accountTo;

    private BigDecimal amount;
}
