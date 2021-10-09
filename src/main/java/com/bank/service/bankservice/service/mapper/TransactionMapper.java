package com.bank.service.bankservice.service.mapper;

import com.bank.service.bankservice.dto.request.TransactionRequestDto;
import com.bank.service.bankservice.dto.response.TransactionResponseDto;
import com.bank.service.bankservice.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements RequestDtoMapper<TransactionRequestDto, Transaction>,
        ResponseDtoMapper<TransactionResponseDto, Transaction> {

    @Override
    public Transaction mapToModel(TransactionRequestDto dto) {
        Transaction transaction = new Transaction();

        transaction.setAmount(dto.getAmount());
        transaction.setAccountTo(dto.getAccountTo());
        transaction.setAccountFrom(dto.getAccountFrom());

        return transaction;
    }

    @Override
    public TransactionResponseDto mapToDto(Transaction model) {
        TransactionResponseDto dto = new TransactionResponseDto();

        dto.setDate(model.getDate());
        dto.setId(model.getId());
        dto.setAmount(model.getAmount());
        dto.setAccountFrom(dto.getAccountFrom());
        dto.setAccountTo(model.getAccountTo());
        dto.setOperationType(model.getOperationType());

        return dto;
    }
}
