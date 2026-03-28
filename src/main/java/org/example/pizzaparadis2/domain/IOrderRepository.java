package org.example.pizzaparadis2.domain;
import java.util.List;

public interface IOrderRepository {

    void updateOrder(Order order);
    void deleteOrder(Order order);
    void createOrder(int pizzaId, int quantity, List<Integer> toppingIds);
}
