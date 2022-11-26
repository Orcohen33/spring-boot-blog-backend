package com.orcohen.blogrestapi.service.impl;

import com.orcohen.blogrestapi.entity.Role;
import com.orcohen.blogrestapi.repository.RoleRepository;
import com.orcohen.blogrestapi.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
