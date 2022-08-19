package ru.kata.spring.boot_security.demo.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;

//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    private final RoleRepository roleRepository;
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public List<User> findAll() {
       return userRepository.findAll();
    }
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
