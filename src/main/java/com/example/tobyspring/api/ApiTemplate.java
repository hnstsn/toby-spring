package com.example.tobyspring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

    private final ApiExecutor defauktApiExecutor;

    private final ExRateExtractor defaultExRateExtractor;

    public ApiTemplate() {
        this.defauktApiExecutor = new HttpClientApiExecutor();
        this.defaultExRateExtractor = new ErApiExRateExtractor();
    }

    public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        this.defauktApiExecutor = apiExecutor;
        this.defaultExRateExtractor = exRateExtractor;
    }


    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.defauktApiExecutor, this.defaultExRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.defaultExRateExtractor);
    }

    public BigDecimal getExRate(String url, ExRateExtractor exRateExtractor) {
        return this.getExRate(url, this.defauktApiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {     // template.
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
