package com.bank.service.bankservice.service.impl;

import com.bank.service.bankservice.dto.ApiResponseDto;
import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Currency;
import com.bank.service.bankservice.model.Transaction;
import com.bank.service.bankservice.repository.TransactionRepository;
import com.bank.service.bankservice.service.AccountService;
import com.bank.service.bankservice.service.HttpClientService;
import com.bank.service.bankservice.service.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final AccountService accountService;

    private final HttpClientService httpClientService;

    public TransactionServiceImpl(TransactionRepository repository,
                                  AccountService accountService,
                                  HttpClientService httpClientService) {
        this.repository = repository;
        this.accountService = accountService;
        this.httpClientService = httpClientService;
    }

    @Override
    public List<Transaction> saveAll(Iterable<Transaction> transactions) {
        return repository.saveAll(transactions);
    }

    @Override
    @Transactional
    public void transfer(Account senderAccount, Account recipientAccount, BigDecimal amount) {
        Currency senderAccountCurrency = senderAccount.getCurrency();
        Currency recipientAccountCurrency = recipientAccount.getCurrency();

        BigDecimal senderAccountBalance = senderAccount.getBalance();
        BigDecimal newSenderAccountBalance = senderAccountBalance.subtract(amount);

        if (!senderAccountCurrency.equals(recipientAccountCurrency)) {
            ApiResponseDto apiResponseDto = httpClientService
                    .convertCurrencyRequest(senderAccountCurrency, recipientAccountCurrency, amount);
            amount = apiResponseDto.getResult();
        }

        BigDecimal recipientAccountBalance = recipientAccount.getBalance();
        BigDecimal newRecipientAccountBalance = recipientAccountBalance.add(amount);

        senderAccount.setBalance(newSenderAccountBalance);
        recipientAccount.setBalance(newRecipientAccountBalance);
        accountService.saveAll(List.of(senderAccount, recipientAccount));
    }

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "date");
        Pageable pageRequest = PageRequest.of(page, size, sortByDate);
        return repository.getAllByAccount(account, pageRequest);
    }
}
