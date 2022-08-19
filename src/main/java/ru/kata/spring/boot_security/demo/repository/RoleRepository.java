package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Collection;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String name);
    Collection<Role> findAllByUsersId(long id);
    Collection<Role> findAll();

}
