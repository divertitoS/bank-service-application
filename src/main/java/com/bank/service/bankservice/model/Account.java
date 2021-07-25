package com.bank.service.bankservice.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal balance;
    @Column(name = "is_Active")
    private boolean isActive;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
