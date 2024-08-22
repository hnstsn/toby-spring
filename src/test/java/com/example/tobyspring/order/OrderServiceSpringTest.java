package com.example.tobyspring.order;

import com.example.tobyspring.config.OrderConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private DataSource dataSource;

    @Test
    public void createOrderTest() {
        Order order = orderService.createOrder("0100", BigDecimal.ONE);
        Assertions.assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    public void createOrders() {
        List<OrderReq> orderReqs = List.of(
                        new OrderReq("0200", BigDecimal.ONE),
                        new OrderReq("0201", BigDecimal.TWO));
        var orders = orderService.createOrders(orderReqs);
        Assertions.assertThat(orders).hasSize(2);
        orders.forEach(order -> Assertions.assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    @DisplayName("중복 테스트")
    public void createDuplicatedOrders() {
        List<OrderReq> orderReqs = List.of(
                        new OrderReq("0300", BigDecimal.ONE),
                        new OrderReq("0300", BigDecimal.TWO));

        Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        JdbcClient client = JdbcClient.create(dataSource);
        Long cnt = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();
        Assertions.assertThat(cnt).isEqualTo(0);    // expected: 0L, but was: 1L -> 그래서 Transaction이 필요
    }
}
