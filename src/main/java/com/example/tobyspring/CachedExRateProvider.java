package com.example.tobyspring;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;

    private BigDecimal cachesExRate;    // USD 한정
    private LocalDateTime cacheExpTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        if (cachesExRate == null || cacheExpTime.isBefore(LocalDateTime.now())) {
            cachesExRate = this.target.getExRate(currency);
            cacheExpTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("cache updated");
        }
        return cachesExRate;
    }
}
