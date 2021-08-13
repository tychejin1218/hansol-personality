package com.eduhansol.app.services;

import com.eduhansol.app.entities.Admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService  extends UserDetailsService{
    Page<Admin> getList(Pageable pageable);
    Page<Admin> getListByIsDelete(Pageable pageable);

    Admin getLogin(String email);
    Admin get(int id);
    int getCount();
    int getCountByIsDelete(boolean isDelete);

    Admin post(Admin model);
    void update(Admin model);
    void delete(int id);
}