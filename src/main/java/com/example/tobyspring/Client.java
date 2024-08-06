package com.example.tobyspring;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider()); // 여기서 설정
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println("payment = " + payment);
    }
}
