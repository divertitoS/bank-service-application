package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.Role;

public interface RoleService {
    Role save(Role role);

    Role findByName(Role.RoleName name);
}
