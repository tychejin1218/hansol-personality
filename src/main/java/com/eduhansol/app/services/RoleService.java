package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Role;

public interface RoleService {
    List<Role> getList();
    Role get(int id);
    void save(Role role);
}