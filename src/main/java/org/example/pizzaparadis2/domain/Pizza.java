package org.example.pizzaparadis2.domain;

import java.util.List;

public class Pizza {

    private int id;
    private String name;
    private String desc;
    private double price;
    private List<Topping> toppings;

    public Pizza() {}

    public Pizza(int id, String name, String desc, double price, List<Topping> toppings) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.toppings = toppings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public double totalPrice() {
        double sum = price;
        for (Topping topping : toppings) {
            sum += topping.getPrice();
        }
        return sum;
    }
}
