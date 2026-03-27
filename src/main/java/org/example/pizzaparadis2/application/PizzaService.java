package org.example.pizzaparadis2.application;


import org.example.pizzaparadis2.domain.IPizzaRepository;
import org.example.pizzaparadis2.domain.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PizzaService {

    private final IPizzaRepository pizzaRepo;

    public PizzaService(IPizzaRepository pizzaRepo) {
        this.pizzaRepo = pizzaRepo;
    }

    public List<Pizza> getListOfPizza() {
        return pizzaRepo.getPizzas();
    }

    public Pizza getPizzaById(int id) {
        return pizzaRepo.getPizzaByID(id);
    }


}
