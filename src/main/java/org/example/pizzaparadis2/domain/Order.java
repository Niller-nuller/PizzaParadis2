package org.example.pizzaparadis2.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private LocalDateTime date;
    private List<Pizza> pizzas;
    private double totalPrice;
    public Order(int orderId, LocalDateTime date, List<Pizza> pizzas, double totalPrice) {
        this.orderId = orderId;
        this.date = date;
        this.pizzas = pizzas;
        this.totalPrice = totalPrice;
    }

    public Order() {}

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public List<Pizza> getPizzas() {
        return pizzas;
    }
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    public double getTotalPrice() {
        return totalPrice;
    }

}
