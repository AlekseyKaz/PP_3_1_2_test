package ru.kata.spring.boot_security.demo;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("any@mail.com");
        user.setName("ivan");
        user.setLastname("ivanov");
        user.setAge(20);
        user.setPassword("any");
        User saveUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, saveUser.getId());
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }
    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepository.findByName("Admin");

        User user = new User();
        user.setEmail("admin@mail.com");
        user.setName("ivan");
        user.setLastname("ivanov");
        user.setAge(20);
        user.setPassword("admin");
        User user1 = userRepository.save(user);
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user1);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);

    }

    @Test
    public void testAddRoleToExistingUser() {
        User user = userRepository.findById(1L).get();
        Role roleUser = roleRepository.findByName("user");
        Role roleCustomer = new Role(3L);

        user.addRole(roleUser);
        user.addRole(roleCustomer);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }
}
