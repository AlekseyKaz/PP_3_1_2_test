package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {
    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User>  showAllUser() {
        List<User> users = userService.findAll();
        return users;
    }
    //
    @GetMapping("/userinfo")
    public User showAutUser(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @GetMapping("/role")
    public List<Role> showRole() {
        List<Role> roles = userService.listRoles();
        return roles;
    }

    @PostMapping("/adduser")
    public User addUser (@RequestBody User user) {
        userService.add(user);
        return user;
    }
    @PutMapping("/edituser") // putMapping связывает запрос, использующий HTTP метод PUT с методом контроллера
    public User updateUser(@RequestBody User user) {
        userService.editUser(user);
        return user;
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "User with Id = " + id + " gone into the sunset";
    }
}
