package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.Account;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account save(Account account);

    List<Account> saveAll(Iterable<Account> accounts);

    List<Account> getAllByUserPhoneNumber(String phoneNumber);

    BigDecimal getBalanceByAccountNumber(String accountNumber);

    Account getByAccountNumber(String accountNumber);
}
