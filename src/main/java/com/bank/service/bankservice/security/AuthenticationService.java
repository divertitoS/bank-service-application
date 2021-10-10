package com.bank.service.bankservice.security;

import com.bank.service.bankservice.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
