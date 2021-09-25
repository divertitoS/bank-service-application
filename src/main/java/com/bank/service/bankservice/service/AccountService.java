package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Currency;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account save(Account account);

    List<Account> saveAll(Iterable<Account> accounts);

    List<Account> getAllByUserPhoneNumber(String phoneNumber);

    BigDecimal getBalanceByAccountNumber(String accountNumber);

    BigDecimal getBalanceByAccountNumberAndCurrency(String accountNumber, Currency currency);
}
