package org.example.pizzaparadis2.presentation;


import jakarta.servlet.http.HttpSession;
import org.example.pizzaparadis2.application.UserService;
import org.example.pizzaparadis2.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService bService) {
        this.userService = bService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        return "user/login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("loggedInUser", null);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(Model model, @RequestParam String email, HttpSession session) {
        User user = userService.login(email);

        if (user == null) {
            model.addAttribute("error", "Ingen bruger fundet med den email");
            return "/user/login";
        }

        session.setAttribute("loggedInUser", user);
        System.out.println("User er: " + user.getEmail() + ", " + user.getName());
        return "redirect:/pizza/list";
    }

    @GetMapping("/{email}/history")
    public String history(Model model, @PathVariable String email) {
        model.addAttribute("orders", userService.getOrderHistory(email));
        return "user/history";
    }
}
