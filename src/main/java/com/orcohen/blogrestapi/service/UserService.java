package com.orcohen.blogrestapi.service;

import com.orcohen.blogrestapi.entity.User;

public interface UserService {

    void saveUser(User user);

    boolean isUserExistByUsername(String username);
    boolean isUserExistByEmail(String email);

}
