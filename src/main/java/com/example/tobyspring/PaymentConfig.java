package com.example.tobyspring;

import com.example.tobyspring.api.ApiTemplate;
import com.example.tobyspring.api.ErApiExRateExtractor;
import com.example.tobyspring.api.SimpleApiExecutor;
import com.example.tobyspring.exrate.CachedExRateProvider;
import com.example.tobyspring.exrate.RestTemplateExRateProvider;
import com.example.tobyspring.payment.ExRateProvider;
import com.example.tobyspring.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@Configuration
@ComponentScan
public class PaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

//    @Bean
    public ExRateProvider cachedExRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
//        return new ApiTemplate();
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ExRateProvider exRateProvider() {
//        return new WebApiExRateProvider(apiTemplate());
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
