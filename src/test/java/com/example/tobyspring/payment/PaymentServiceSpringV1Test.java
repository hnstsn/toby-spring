package com.example.tobyspring.payment;

import com.example.tobyspring.TestPaymentConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceSpringV1Test {


    @Test
    void convertedAmount() {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(TestPaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(1L, "USD", valueOf(10));

        // 환율정보
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

//        // 유효시간 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
    }

}