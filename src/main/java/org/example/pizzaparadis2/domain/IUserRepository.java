package org.example.pizzaparadis2.domain;

import java.util.List;

public interface IUserRepository {

    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    List<Order> requestGettUseresOrderList(User user);
    User logUserIn(User user);
}
