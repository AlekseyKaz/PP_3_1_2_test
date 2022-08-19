package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import ru.kata.spring.boot_security.demo.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();
    User findByEmail(String email);
}
