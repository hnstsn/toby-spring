package com.example.tobyspring.exrate;

import com.example.tobyspring.api.ApiTemplate;
import com.example.tobyspring.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

//        return apiTemplate.getExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
//        return apiTemplate.getExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
        return apiTemplate.getExRate(url);
    }
}
