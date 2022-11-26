package com.orcohen.blogrestapi.service;

import com.orcohen.blogrestapi.entity.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findByName(String name);

}
