package com.example.tobyspring.config;

import com.example.tobyspring.data.JdbcOrderRepository;
import com.example.tobyspring.order.OrderRepository;
import com.example.tobyspring.order.OrderService;
import com.example.tobyspring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {
    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public OrderRepository orderRepository(DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }
}
