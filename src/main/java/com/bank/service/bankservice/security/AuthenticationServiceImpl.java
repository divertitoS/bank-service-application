package com.bank.service.bankservice.security;

import com.bank.service.bankservice.model.Role;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.service.RoleService;
import com.bank.service.bankservice.service.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();

        user.setEmail(email);
        user.setPassword(password);

        Role userRole = roleService.getByName(Role.RoleName.USER);
        user.setRoles(Set.of(userRole));

        userService.save(user);

        return user;
    }
}
