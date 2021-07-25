package com.bank.service.bankservice.service;

import com.bank.service.bankservice.model.User;

public interface UserService {
    User save(User user);

    User getUserInfo(Long id);

    User findByPhoneNumber(String phoneNumber);

    void remove(Long id);
}
