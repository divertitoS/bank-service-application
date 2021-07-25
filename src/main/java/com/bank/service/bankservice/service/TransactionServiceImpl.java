package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import com.bank.service.bankservice.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    @Override
    public List<Transaction> saveAll(Iterable<Transaction> transactions) {
        return repository.saveAll(transactions);
    }

    @Override
    @Transactional
    public void transfer(Account senderAccount, Account recipientAccount, BigDecimal amount) {
        BigDecimal senderAccountBalance = accountService
                .getBalanceByAccountNumberAndCurrency(senderAccount.getAccountNumber(),
                        senderAccount.getCurrency());
        BigDecimal newSenderAccountBalance = senderAccountBalance.subtract(amount);
        senderAccount.setBalance(newSenderAccountBalance);

        BigDecimal recipientAccountBalance = accountService
                .getBalanceByAccountNumberAndCurrency(recipientAccount.getAccountNumber(),
                        recipientAccount.getCurrency());
        BigDecimal newRecipientAccountBalance = recipientAccountBalance.add(amount);
        recipientAccount.setBalance(newRecipientAccountBalance);

        accountService.saveAll(List.of(senderAccount, recipientAccount));

        Transaction senderTransaction = new Transaction(
                senderAccount,
                recipientAccount,
                LocalDateTime.now(),
                amount,
                Transaction.Operation.OUTCOMING
        );

        Transaction recipientTransaction = new Transaction(
                senderAccount,
                recipientAccount,
                LocalDateTime.now(),
                amount,
                Transaction.Operation.INCOMING
        );

        saveAll(List.of(senderTransaction, recipientTransaction));
    }

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        Pageable pageRequest = PageRequest.of(page, size);
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "date");
        return repository.getAllByAccount(account, pageRequest, sortByDate);
    }
}
