package org.example.pizzaparadis2.application;


import org.example.pizzaparadis2.domain.IOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final IOrderRepository orderRepo;

    public OrderService(IOrderRepository oRepository) {
        this.orderRepo = oRepository;
    }

}
