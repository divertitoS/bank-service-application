package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    List<Transaction> saveAll(Iterable<Transaction> transactions);

    List<Transaction> transfer(Account senderAccount, Account recipientAccount, BigDecimal amount);

    List<Transaction> getAllByAccount(int page, int size, Account account);
}
