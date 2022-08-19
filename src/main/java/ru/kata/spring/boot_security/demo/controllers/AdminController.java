package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
private final UserService userService;
private final PasswordEncoder passwordEncoder;

    public AdminController( UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin")
    public String showUserList(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("users",userList);
//        model.addAttribute("users.roles",userList);
        return "admin";
    }
    @GetMapping("/signup")
    public String showUserSignFrom(User user) {
        return "add-user";

    }
    @PostMapping("/adduser")
    public String addUser(User user, Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        List<Role> roleList = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles",roleList);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,User user, Model model) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}

