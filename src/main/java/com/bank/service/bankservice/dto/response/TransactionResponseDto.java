package com.bank.service.bankservice.dto.response;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransactionResponseDto {

    private Long id;

    private Account accountFrom;

    private Account accountTo;

    private BigDecimal amount;

    private LocalDateTime date;

    private Transaction.Operation operationType;
}
