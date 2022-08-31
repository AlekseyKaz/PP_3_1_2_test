package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @GetMapping("/users")
//    public String showUserList(Model model,Principal principal) {
//        User user = userService.findByEmail(principal.getName());
//        return "áddasda " + principal.getName();
//    }
//    @GetMapping("/user")
//    public String user(Model model,Principal principal) {
//        User user = userService.findByEmail(principal.getName());
//        model.addAttribute("user", user);
//        return "/user";
//    }
    @GetMapping("/user")
    public String index(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user",user);
        return "user";
    }
}
