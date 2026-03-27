package org.example.pizzaparadis2.infrastructure;

import org.example.pizzaparadis2.domain.IUserRepository;
import org.example.pizzaparadis2.domain.Order;
import org.example.pizzaparadis2.domain.User;
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
    public List<Order> getHistoryFromEmail(String email) {
        return null;
    }
}
