package com.bank.service.bankservice.service.impl;

import com.bank.service.bankservice.exception.DataProcessingException;
import com.bank.service.bankservice.model.User;
import com.bank.service.bankservice.repository.UserRepository;
import com.bank.service.bankservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserInfo(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new DataProcessingException("User with identifier " + id + " does not exist"));
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber).orElseThrow(() ->
                new DataProcessingException("User with phone number"
                + phoneNumber + "does not exist"));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() ->
                new DataProcessingException("User with email "
                        + email + " does not exist"));
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
}
