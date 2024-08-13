package com.example.tobyspring.payment;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;


class PaymentServiceTest {

    @Test
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000));
        testAmount(valueOf(3_000), valueOf(30_000));

//        // 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));
        Payment payment = paymentService.prepare(1L, "USD", valueOf(10));
        // 환율정보
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}