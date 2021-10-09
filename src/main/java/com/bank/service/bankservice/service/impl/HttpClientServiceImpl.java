package com.bank.service.bankservice.service.impl;

import com.bank.service.bankservice.dto.response.ApiResponseDto;
import com.bank.service.bankservice.exception.HttpProcessingException;
import com.bank.service.bankservice.model.Currency;
import com.bank.service.bankservice.service.HttpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HttpClientServiceImpl implements HttpClientService {

    private final Environment environment;

    private final ObjectMapper objectMapper;

    private final CloseableHttpClient httpClient;

    public HttpClientServiceImpl(Environment environment, ObjectMapper objectMapper) {
        this.environment = environment;
        this.objectMapper = objectMapper;
        this.httpClient = HttpClients.createDefault();
    }

    @Override
    public ApiResponseDto convertCurrencyRequest(Currency from, Currency to, BigDecimal amount) {
        String url = environment.getProperty("exchange.rate.url");

        String urlWithParameters = new StringBuilder()
                .append(url)
                .append("?from=").append(from.name())
                .append("&to=").append(to.name())
                .append("&amount=").append(amount.toString())
                .toString();

        HttpGet request = new HttpGet(urlWithParameters);

        ApiResponseDto apiResponseDto;

        try {
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                Optional<HttpEntity> entityWrapper = Optional.ofNullable(response.getEntity());
                String content = EntityUtils.toString(entityWrapper.orElseThrow(() ->
                        new HttpProcessingException(
                                "Can't get a result of the http request by current url: " + url)));
                apiResponseDto = objectMapper.readValue(content, ApiResponseDto.class);
            }
        } catch (IOException e) {
            throw new HttpProcessingException("Can't send GET request to url: " + url, e);
        }
        return apiResponseDto;
    }
}
