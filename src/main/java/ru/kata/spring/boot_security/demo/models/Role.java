package ru.kata.spring.boot_security.demo.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
@Data

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }

    public Role(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name ;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
    public String getRoleAsString() {
        String s = "";
        if (name.contains("ROLE_ADMIN")) {
            s = "Amin";
        } else if (name.contains("ROLE_USER")) {
            s = "User";
        }
        return s;
    }
}
