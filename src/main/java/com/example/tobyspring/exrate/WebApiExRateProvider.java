package com.example.tobyspring.exrate;

import com.example.tobyspring.api.*;
import com.example.tobyspring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    private ApiTemplate apiTemplate = new ApiTemplate();

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

//        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }
}
