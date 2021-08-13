package com.eduhansol.app.services;

import java.util.ArrayList;
import java.util.List;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Admin;
import com.eduhansol.app.entities.Role;
import com.eduhansol.app.repositories.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository _adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        _adminRepository = adminRepository;
    }

    /**
     * Spring Security
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Admin admin = _adminRepository.findByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();

        Role role = admin.getRole();

        if (role.getName().equals(Constants.ROLE_ADMIN)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (role.getName().equals(Constants.ROLE_HQ)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_HQ"));

        } else if (role.getName().equals(Constants.ROLE_REGION)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_REGION"));

        } else if (role.getName().equals(Constants.ROLE_DPT)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_DPT"));
        }

        String upperEmail = admin.getEmail().toUpperCase();

        return new User(upperEmail, admin.getPassword(), authorities);
    }

    @Override
    public Page<Admin> getList(Pageable pageable) {
        return _adminRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> getListByIsDelete(Pageable pageable){
        return _adminRepository.findByIsDelete(pageable, false);
    }

    @Override
    public Admin getLogin(String email) {
        return _adminRepository.findByEmail(email);
    }

    @Override
    public Admin get(int id) {
        return _adminRepository.findById(id).get();
    }

    @Override
    public int getCount() {
        return (int) _adminRepository.count();
    }

    @Override
    public int getCountByIsDelete(boolean isDelete) {
        return (int) _adminRepository.countByIsDelete(isDelete);
    }

    @Override
    public Admin post(Admin model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        return _adminRepository.save(model);
    }

    @Override
    public void update(Admin model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        _adminRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Admin admin = _adminRepository.findById(id).get();
        if (admin == null)
            return;
        _adminRepository.delete(admin);
    }

}