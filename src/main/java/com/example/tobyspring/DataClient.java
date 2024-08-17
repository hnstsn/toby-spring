package com.example.tobyspring;

import com.example.tobyspring.data.OrderRepository;
import com.example.tobyspring.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);
        System.out.println("order = " + order);

        Order order2 = new Order("100", BigDecimal.TWO);
        orderRepository.save(order2);   // ConstraintViolationException 발생
    }
}
