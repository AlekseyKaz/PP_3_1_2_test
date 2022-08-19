package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //private final UserService userService;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

//    public UserDetailsServiceImpl(UserService userService, RoleRepository roleRepository) {
//        this.userService = userService;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        user.setRoles(roleRepository.findAllById(Collections.singleton(user.getId())));// !!!!!! обрати внимание !!!!
        if (user == null) {
            throw new UsernameNotFoundException(String.format("'%s' -- неопознанное тело", username));
        }

        return user;
    }
//    private Collection<? extends GrantedAuthority> mapToAuthorities(Collection<Role> roles){
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }
}
