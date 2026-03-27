package org.example.pizzaparadis2.domain;

import java.util.List;

public interface IPizzaRepository {

    void savePizza(Pizza pizza);
    void updatePizza(Pizza pizza);
    void deletePizza(Pizza pizza);
    List<Pizza> getPizzas();
    List<Topping> getToppings();
    Pizza getPizzaByID(int ID);
}
