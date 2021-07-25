package com.bank.service.bankservice.model;

import com.bank.service.bankservice.service.RoleService;
import com.bank.service.bankservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_from")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account accountFrom;
    @Column(name = "account_to")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account accountTo;
    private BigDecimal amount;
    private LocalDateTime date;
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private Operation operationType;

    public Transaction(
            Account accountFrom,
            Account accountTo,
            LocalDateTime date,
            BigDecimal amount,
            Operation operationType
    ) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public enum Operation {
        INCOMING,
        OUTCOMING
    }
}
