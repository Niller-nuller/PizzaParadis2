package org.example.pizzaparadis2.presentation;


import org.example.pizzaparadis2.application.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public String createOrder(
            @RequestParam int PizzaId,
            @RequestParam int Quantity,
            @RequestParam(required = false)List<Integer> toppingIds
            ) {
        orderService.CreateOrder(PizzaId, Quantity, toppingIds);
        return "redirect:/";
    }
}
