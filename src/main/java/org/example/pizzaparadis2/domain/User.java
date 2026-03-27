package org.example.pizzaparadis2.domain;

public class User {

    private int id;
    private String name;
    private String email;
    private String address;
    private int bonusPoints;

    public User(int id, String name, String email, String address, int bonusPoints) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.bonusPoints = bonusPoints;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
