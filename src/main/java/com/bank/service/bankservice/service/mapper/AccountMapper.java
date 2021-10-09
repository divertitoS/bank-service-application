package com.bank.service.bankservice.service.mapper;

import com.bank.service.bankservice.dto.request.AccountRequestDto;
import com.bank.service.bankservice.dto.response.AccountResponseDto;
import com.bank.service.bankservice.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements RequestDtoMapper<AccountRequestDto, Account>,
        ResponseDtoMapper<AccountResponseDto, Account> {

    @Override
    public Account mapToModel(AccountRequestDto dto) {
        Account account = new Account();
        account.setBalance(dto.getBalance());
        account.setAccountNumber(dto.getAccountNumber());
        account.setCurrency(dto.getCurrency());
        account.setUser(dto.getUser());
        return account;
    }

    @Override
    public AccountResponseDto mapToDto(Account model) {
        AccountResponseDto dto = new AccountResponseDto();
        dto.setAccountNumber(model.getAccountNumber());
        dto.setActive(model.isActive());
        dto.setBalance(model.getBalance());
        dto.setCurrency(model.getCurrency());
        dto.setUser(model.getUser());
        dto.setId(model.getId());
        return dto;
    }
}
