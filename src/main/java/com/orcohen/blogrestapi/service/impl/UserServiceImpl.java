package com.orcohen.blogrestapi.service.impl;

import com.orcohen.blogrestapi.entity.User;
import com.orcohen.blogrestapi.repository.UserRepository;
import com.orcohen.blogrestapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUserExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
