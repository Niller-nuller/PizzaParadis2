package org.example.pizzaparadis2.infrastructure;

import org.example.pizzaparadis2.domain.IPizzaRepository;
import org.example.pizzaparadis2.domain.Pizza;
import org.example.pizzaparadis2.domain.Topping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PizzaRepository implements IPizzaRepository {

    private final JdbcTemplate jdbcTemplate;

    public PizzaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void savePizza(Pizza pizza) {

    }

    @Override
    public void updatePizza(Pizza pizza) {

    }

    @Override
    public void deletePizza(Pizza pizza) {

    }

    @Override
    public List<Pizza> getPizzas(){
        String sql = "SELECT * FROM Pizza ORDER BY Price DESC";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Pizza (
                        rs.getInt("PizzaId"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        getToppingsByPizzaId(rs.getInt("PizzaId"))
                )
        );
    }

    private List<Topping> getToppingsByPizzaId(int pizzaId) {
        String sql = "SELECT * FROM Toppings T JOIN Pizza_Toppings PT ON T.ToppingId = PT.ToppingId WHERE PT.PizzaId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Topping (
                        rs.getInt("ToppingId"),
                        rs.getString("Name"),
                        rs.getDouble("Price")
                ), pizzaId
        );
    }

    @Override
    public List<Topping> getToppings(){
        String sql = "SELECT * FROM Toppings";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Topping(
                        rs.getInt("ToppingId"),
                        rs.getString("Name"),
                        rs.getDouble("Price")
                )
        );
    }

    @Override
    public Pizza getPizzaByID(int ID) {
        String sql = "SELECT * FROM Pizza WHERE PizzaId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Pizza pizza = new Pizza();
            pizza.setName(rs.getString("Name"));
            pizza.setDesc(rs.getString("Description"));
            pizza.setPrice(rs.getDouble("Price"));
            pizza.setToppings(getToppingsByPizzaId(rs.getInt("PizzaId")));
            return pizza;
        }, ID);
    }
}
