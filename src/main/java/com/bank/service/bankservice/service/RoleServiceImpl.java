package com.bank.service.bankservice.service;

import com.bank.service.bankservice.exception.DataProcessingException;
import com.bank.service.bankservice.model.Role;
import com.bank.service.bankservice.repository.RoleRepository;
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
    public Role findByName(Role.RoleName name) {
        return repository.findByName(name).orElseThrow(() ->
                new DataProcessingException("Role named " + name.toString() + " does not exist"));
    }
}
