package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

@Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.entityManager = entityManager;
}

    @Override
    @Transactional
    public void save(User user) {
//    user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

//    @Override
//    @Transactional
//    public void editUser(long id, User user) {
//        if(user.getPassword() == null|| user.getPassword().equals(userRepository.getById(id).getPassword())) {
//            user.setPassword(user.getPassword());
//        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//        userRepository.save(user);
//    }

}
