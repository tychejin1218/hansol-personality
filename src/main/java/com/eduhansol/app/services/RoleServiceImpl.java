package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Role;
import com.eduhansol.app.repositories.RoleRepository;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository _roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        _roleRepository = roleRepository;
    }

    @Override
    public List<Role> getList() {
        return _roleRepository.findAll();
    }

    @Override
    public Role get(int id) {
        return _roleRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Role role) {
        _roleRepository.save(role);
    }
}
