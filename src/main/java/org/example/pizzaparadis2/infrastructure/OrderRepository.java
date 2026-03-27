package org.example.pizzaparadis2.infrastructure;


import org.example.pizzaparadis2.domain.IOrderRepository;
import org.example.pizzaparadis2.domain.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository implements IOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createOrder(Order order) {

    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(Order order) {

    }
}
