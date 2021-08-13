package com.eduhansol.app.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eduhansol.app.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByEmail(String email);
    Page<Admin> findByIsDelete(Pageable pageable, boolean isDelete);
    long countByIsDelete(boolean isDelete);
}