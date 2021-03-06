package com.bank.service.bankservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import lombok.Data;

@Data
public class ApiResponseDto {

    private boolean success;

    private Map<String, String> query;

    private Map<String, Double> info;

    private boolean historical;

    private LocalDate date;

    private BigDecimal result;
}
