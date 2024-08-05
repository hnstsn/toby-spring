package com.example.tobyspring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class PaymentService {
    private final ExRateProvider exRateProvider;

    public PaymentService() {
        this.exRateProvider = new WebApiExRateProvider();
//        this.exRateProvider = new SimpleExRateProvider();
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
        BigDecimal exRate = exRateProvider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);
        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

    // 환율 가져오기

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment = " + payment);
    }
}