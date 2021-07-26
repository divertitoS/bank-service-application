package com.bank.service.bankservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "account_from")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account accountFrom;
    @JoinColumn(name = "account_to")
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
