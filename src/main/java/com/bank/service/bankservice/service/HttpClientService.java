package com.bank.service.bankservice.service;

import com.bank.service.bankservice.dto.response.ApiResponseDto;
import com.bank.service.bankservice.model.Currency;
import java.math.BigDecimal;

public interface HttpClientService {

    ApiResponseDto convertCurrencyRequest(Currency from, Currency to, BigDecimal amount);
}
