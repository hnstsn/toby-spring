package com.example.tobyspring;

import com.example.tobyspring.data.OrderRepository;
import com.example.tobyspring.order.Order;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository orderRepository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            // transaction
            new TransactionTemplate(transactionManager)
                    .execute((TransactionCallback<Order>) status -> {
                        Order order = new Order("100", BigDecimal.TEN);
                        orderRepository.save(order);
                        System.out.println("order = " + order);

                        Order order2 = new Order("100", BigDecimal.TWO);
                        orderRepository.save(order2);

                        return null;
                    });
        } catch (DataIntegrityViolationException e) {   // jdbc, mybatis, jpa 등을 사용해도 catch에서 처리 가능
            System.out.println("주문번호 중복 복구 작업");
        }
    }
}
