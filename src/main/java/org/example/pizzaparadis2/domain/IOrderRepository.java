package org.example.pizzaparadis2.domain;


public interface IOrderRepository {

    void createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Order order);

}
