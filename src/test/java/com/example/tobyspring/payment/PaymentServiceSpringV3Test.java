package com.example.tobyspring.payment;

import com.example.tobyspring.TestObjectFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringV3Test {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ExRateProviderStub exRateProviderStub;

    @Test
    void convertedAmount() {
        // exRate 1000(기본값)
        Payment payment = paymentService.prepare(1L, "USD", valueOf(10));

        // 환율정보
        assertThat(payment.getExRate()).isEqualByComparingTo(valueOf(1_000));

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // exRate 500(변경)
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", valueOf(10));
        assertThat(payment2.getExRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

}