package com.bank.service.bankservice.service.impl;

import com.bank.service.bankservice.exception.DataProcessingException;
import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Currency;
import com.bank.service.bankservice.repository.AccountRepository;
import com.bank.service.bankservice.service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public List<Account> saveAll(Iterable<Account> accounts) {
        return repository.saveAll(accounts);
    }

    @Override
    public List<Account> getAllByUserPhoneNumber(String phoneNumber) {
        return repository.getAccountsByUserPhoneNumber(phoneNumber);
    }

    @Override
    public BigDecimal getBalanceByAccountNumber(String accountNumber) {
        return repository.findAccountByAccountNumber(accountNumber).orElseThrow(() ->
                new DataProcessingException("There is no account with this phone number"))
                .getBalance();
    }

    @Override
    public BigDecimal getBalanceByAccountNumberAndCurrency(String accountNumber,
                                                           Currency currency) {
        return repository.findAccountByAccountNumberAndCurrency(accountNumber, currency)
                .orElseThrow(() -> new DataProcessingException(
                        "There is no account with this phone number")).getBalance();
    }
}
