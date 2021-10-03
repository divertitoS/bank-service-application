package com.bank.service.bankservice.service.mapper;

public interface RequestDtoMapper<D, T> {

    T mapToModel(D dto);
}
