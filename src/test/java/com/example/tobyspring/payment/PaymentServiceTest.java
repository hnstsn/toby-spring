package com.example.tobyspring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;


class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    public void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000), this.clock);
        testAmount(valueOf(3_000), valueOf(30_000), this.clock);
    }


    // 유효시간 계산
    @Test
    public void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedTime = now.plusMinutes(30);
        Assertions.assertThat(expectedTime).isEqualTo(payment.getValidUntil());
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L, "USD", valueOf(10));
        // 환율정보
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}