package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
private final UserService userService;
private final RoleService roleService;

    public UserDetailsServiceImpl(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("'%s' -- неопознанное тело",username ));
        }
        user.setRoles(roleService.findAllByUsersId(user.getId()));
        return user;
    }
//    private Collection<? extends GrantedAuthority> mapToAuthorities(Collection<Role> roles){
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }
}
