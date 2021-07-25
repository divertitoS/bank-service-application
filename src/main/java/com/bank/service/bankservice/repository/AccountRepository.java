package com.bank.service.bankservice.repository;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> getAccountsByUserPhoneNumber(String phoneNumber);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Optional<Account> findAccountByAccountNumberAndCurrency(String accountNumber, Currency currency);
}