package org.example.pizzaparadis2.presentation;


import org.example.pizzaparadis2.application.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizza/list")
    public String pizzaList(Model model) {
        model.addAttribute("pizzas", pizzaService.getListOfPizza());
        return "pizza/list";
    }

    @GetMapping("/pizza/{id}")
    public String pizzaDetail(@PathVariable int id, Model model) {
        model.addAttribute("pizza", pizzaService.getPizzaById(id));
        return "pizza/details";
    }

}
