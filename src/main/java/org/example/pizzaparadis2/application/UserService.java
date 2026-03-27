package org.example.pizzaparadis2.application;


import org.example.pizzaparadis2.domain.IUserRepository;
import org.example.pizzaparadis2.domain.Order;
import org.example.pizzaparadis2.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final IUserRepository userRepo;

    public UserService(IUserRepository bRepository) {
        this.userRepo = bRepository;
    }


    public void createUser(User user){
        userRepo.createUser(user);
    }

    public void deleteUser(User user){
        userRepo.deleteUser(user);
    }

    public void updateUser(User user){
        userRepo.updateUser(user);
    }

    public List<Order> getOrderHistory(String email){
        return userRepo.getHistoryFromEmail(email);
    }
    public User login(String email){
        return (userRepo.logUserIn(setNewUser(email)));
    }
    private User setNewUser(String email){
        User user = new User();
        user.setEmail(email);
        return user;
    }
}
