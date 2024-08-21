package com.example.tobyspring.data;

import com.example.tobyspring.order.Order;
import com.example.tobyspring.order.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Order order) {
        entityManager.persist(order);
    }
}
