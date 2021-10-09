package com.bank.service.bankservice.util;

import com.bank.service.bankservice.model.Role;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.service.RoleService;
import com.bank.service.bankservice.service.UserService;
import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    void inject() {
        Role adminRole = new Role();
        adminRole.setName(Role.RoleName.ADMIN);
        Role savedAdminRole = roleService.save(adminRole);

        User admin = new User();
        admin.setName("admin");
        admin.setPassword("admin123");
        admin.setEmail("admin123@gmail.com");
        admin.setPhoneNumber("+380228148822");
        admin.setDateOfBirth(LocalDate.now());
        admin.setRoles(Set.of(savedAdminRole));
        userService.save(admin);

        Role userRole = new Role();
        userRole.setName(Role.RoleName.USER);
        Role savedUserRole = roleService.save(userRole);

        User user = new User();
        user.setName("Stanislav");
        user.setPassword("password");
        user.setEmail("astanislav2361@gmail.com");
        user.setPhoneNumber("+380689643543");
        user.setDateOfBirth(LocalDate.now());
        user.setRoles(Set.of(savedUserRole));
        userService.save(user);
    }
}
