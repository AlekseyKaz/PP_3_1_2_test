package ru.kata.spring.boot_security.demo.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
private final UserServiceImpl userService;
private final PasswordEncoder passwordEncoder;

    public AdminController( UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping()
    public String showUserList(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        List<User> userList = userService.findAll();
        List<Role> roleList = userService.listRoles();
        model.addAttribute("users",userList);
        model.addAttribute("roles",roleList);
        model.addAttribute("user",user);// внес изменения. что бы получить текующую роль

        return "admin";
    }
    @GetMapping("/signup")
    public String showUserSignFrom( Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles",userService.listRoles());
        model.addAttribute("newUser", new User());
        return "add-user";

    }
    @PostMapping("/adduser")
    public String addUser(User user,@NotNull Model model) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        model.addAttribute("user", user);
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, @NotNull Model model) {
        User user = userService.findById(id);
        List<Role> roleList = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles",roleList);
        return "redirect:/admin";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id,User user) {
        if(user.getPassword() == null|| user.getPassword().equals(userService.findById(id).getPassword())) {
            user.setPassword(user.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping ("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}

