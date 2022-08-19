package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.Collection;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }
    Role findByName(String name) {
       return roleRepository.findByName(name);
    }
    Collection<Role> findAllByUsersId(long id) {
        return roleRepository.findAllByUsersId(id);
    }
    Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
