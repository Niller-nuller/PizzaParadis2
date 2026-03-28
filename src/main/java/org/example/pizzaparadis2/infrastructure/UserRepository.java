package org.example.pizzaparadis2.infrastructure;

import org.example.pizzaparadis2.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO UserAccount (Name,Email,Address) VALUES(?,?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getAddress());
    }

    @Override
    public void updateUser(User user) {

    }
    @Override
    public User logUserIn(User user) {
        String sql = "SELECT * FROM UserAccount WHERE Email = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new User(
                                rs.getInt("UserId"),
                                rs.getString("Name"),
                                rs.getString("Email"),
                                rs.getString("Address"),
                                rs.getInt("BonusPoint")
                        ), user.getEmail())
                .stream()
                .findFirst()
                .orElse(null);
    }


    @Override
    public void deleteUser(User user) {
        String sql = "DELETE FROM UserAccount WHERE id = ?";
        jdbcTemplate.update(sql, user.getId());
    }

    @Override
    public List<Order> requestGettUseresOrderList(User user) {
        String sql = """
                SELECT * FROM Orders 
                WHERE O.UserId = ?
                ORDER BY O.OrderDate DESC""";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Order(
                        rs.getInt("OrderId"),
                        rs.getTimestamp("OrderDate").toLocalDateTime(),
                        requestGetPizzasOrderList(rs.getInt("OrderId")),
                        rs.getDouble("TotalPrice")
                ),user.getId()
        );
    }

    private List<Pizza> requestGetPizzasOrderList(int orderId) {
        String sql = """
                 SELECT * FROM Pizza P
                 JOIN Order_Items OI ON P.PizzaId = OI.PizzaId
                 WHERE OI.OrderId = ?""";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Pizza(
                        rs.getInt("PizzaId"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        requestGetToppingsByPizzaId(rs.getInt("PizzaId"))
                ), orderId
        );
    }
    private List<Topping> requestGetToppingsByPizzaId(int pizzaId) {
        String sql = "SELECT * FROM Toppings T JOIN Pizza_Toppings PT ON T.ToppingId = PT.ToppingId WHERE PT.PizzaId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Topping (
                        rs.getInt("ToppingId"),
                        rs.getString("Name"),
                        rs.getDouble("Price")
                ), pizzaId
        );
    }
}
