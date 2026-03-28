package org.example.pizzaparadis2.application;


import org.example.pizzaparadis2.domain.IOrderRepository;
import org.example.pizzaparadis2.domain.Order;
import org.example.pizzaparadis2.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final IOrderRepository orderRepo;

    public OrderService(IOrderRepository oRepository) {
        this.orderRepo = oRepository;
    }
    public void CreateOrder(int pizzaId, int quantity, List<Integer> toppingIds) {
        orderRepo.createOrder(pizzaId, quantity, toppingIds);
    }
}
