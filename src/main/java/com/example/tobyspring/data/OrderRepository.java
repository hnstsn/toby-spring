package com.example.tobyspring.data;

import com.example.tobyspring.order.Order;

public interface OrderRepository {
    void save(Order order);
}
