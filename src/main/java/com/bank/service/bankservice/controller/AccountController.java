package com.bank.service.bankservice.controller;

import com.bank.service.bankservice.dto.request.AccountRequestDto;
import com.bank.service.bankservice.dto.request.TransactionRequestDto;
import com.bank.service.bankservice.dto.response.AccountResponseDto;
import com.bank.service.bankservice.dto.response.TransactionResponseDto;
import com.bank.service.bankservice.model.Account;
import com.bank.service.bankservice.model.Transaction;
import com.bank.service.bankservice.service.AccountService;
import com.bank.service.bankservice.service.TransactionService;
import com.bank.service.bankservice.service.mapper.AccountMapper;
import com.bank.service.bankservice.service.mapper.TransactionMapper;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/accounts")
public class AccountController {

    private final TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    private final AccountService accountService;

    private final AccountMapper accountMapper;

    public AccountController(TransactionService transactionService,
                             TransactionMapper transactionMapper, AccountService accountService, AccountMapper accountMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @PostMapping
    public AccountResponseDto create(@RequestBody AccountRequestDto dto) {
        Account account = accountMapper.mapToModel(dto);
        account.setActive(true);
        Account savedAccount = accountService.save(account);
        return accountMapper.mapToDto(savedAccount);
    }

    @GetMapping("/by-phone")
    public List<AccountResponseDto> getAllByUserPhoneNumber(@RequestParam String phoneNumber) {
        List<Account> allCurrentUserAccounts = accountService.getAllByUserPhoneNumber(phoneNumber);
        return allCurrentUserAccounts.stream()
                .map(accountMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/transfer")
    public TransactionResponseDto transfer(@RequestBody TransactionRequestDto dto) {
        Account recipientAccount = dto.getAccountTo();
        Account senderAccount = dto.getAccountFrom();
        BigDecimal amount = dto.getAmount();

        List<Transaction> transactions = transactionService.transfer(senderAccount, recipientAccount, amount);

        return transactions.stream()
                .filter(transaction -> transaction.getOperationType().equals(Transaction.Operation.OUTCOMING))
                .map(transactionMapper::mapToDto)
                .findFirst().get();

    }

    @GetMapping("/{accountNumber}")
    public AccountResponseDto getInfo(@PathVariable String accountNumber) {
        Account account = accountService.getByAccountNumber(accountNumber);
        return accountMapper.mapToDto(account);
    }

    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponseDto> getHistory(@PathVariable String accountNumber,
                                                   @RequestParam(required = false, defaultValue = "0") int page,
                                                   @RequestParam(required = false, defaultValue = "10") int size) {
        Account account = accountService.getByAccountNumber(accountNumber);
        List<Transaction> transactions = transactionService.getAllByAccount(page, size, account);

        return transactions.stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{accountNumber}")
    public void block(@PathVariable String accountNumber) {
        Account account = accountService.getByAccountNumber(accountNumber);
        account.setActive(false);
        accountService.save(account);
    }
}
