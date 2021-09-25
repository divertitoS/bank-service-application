package com.bank.service.bankservice.service.impl;

import com.bank.service.bankservice.exception.DataProcessingException;
import com.bank.service.bankservice.model.Role;
import com.bank.service.bankservice.repository.RoleRepository;
import com.bank.service.bankservice.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public Role getByName(Role.RoleName name) {
        return repository.findRoleByName(name).orElseThrow(() ->
                new DataProcessingException("Role named " + name.toString() + " does not exist"));
    }
}
