package org.example.pizzaparadis2.infrastructure;


import org.example.pizzaparadis2.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createOrder(int pizzaId, int quantity, List<Integer> toppingsIds) {

        KeyHolder orderkey = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Orders (UserId, TotalPrice) values (?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, 1);
            ps.setInt(2, 0);
            return ps;
        }, orderkey);
        int orderId = orderkey.getKey().intValue();

        KeyHolder itemKey = new GeneratedKeyHolder();

        jdbcTemplate.update(conn ->  {
            PreparedStatement ps = conn.prepareStatement(
                    """
                        INSERT INTO Order_Item (OrderId, PizzaId, Price)
                        SELECT ?, PizzaId, ?, Price * ?
                        From Pizza
                        where PizzaId = ?
                        """,
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, orderId);
            ps.setInt(2, quantity);
            ps.setInt(3, quantity);
            ps.setInt(4, pizzaId);
            return ps;
        }, itemKey);

        int orderItemId = itemKey.getKey().intValue();

        if (toppingsIds != null) {
            for (Integer toppingId : toppingsIds) {
                jdbcTemplate.update(
                        "INSERT INTO Order_Items_Toppings (OrderItemId, ToppingId) VALUES (?, ?)",
                        orderItemId,
                        toppingId
                );
            }
        }
        jdbcTemplate.update("""
            UPDATE Order_Items oi
            SET Price = oi.Price + (
                SELECT COALESCE(SUM(t.Price), 0)
                FROM Order_items_Toppings oit
                JOIN Toppings t ON t.ToppingId = oit.ToppingId
                WHERE oit.OrderItemId = oi.OrderItemId
                )
                WHERE oi.OrderItemId = ?
            """, orderItemId);

        jdbcTemplate.update("""
            UPDATE Orders
            SET TotalPrice = (
                SELECT SUM(Price)
                From Order_Items
                Where OrderId = ?
            )
            where OrderId = ?
        """, orderId);
    }


    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void deleteOrder(Order order) {

    }

}
