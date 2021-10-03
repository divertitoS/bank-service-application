package com.bank.service.bankservice.service.mapper;

public interface ResponseDtoMapper<D, T> {

    D mapToDto(T model);
}
