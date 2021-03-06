package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.User;

public interface UserService {
    User save(User user);

    User getUserInfo(Long id);

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    void remove(Long id);
}
