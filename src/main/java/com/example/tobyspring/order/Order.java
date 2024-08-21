package com.example.tobyspring.order;

import java.math.BigDecimal;

public class Order {
    private Long id;    // 주문번호

    private String no;  // 주문번호(유저용)

    private BigDecimal total;   // 총 지불 금액

    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}
