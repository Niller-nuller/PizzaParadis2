package org.example.pizzaparadis2.presentation;


import org.example.pizzaparadis2.application.OrderService;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}
