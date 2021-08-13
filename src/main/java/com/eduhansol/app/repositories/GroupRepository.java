package com.eduhansol.app.repositories;

import com.eduhansol.app.entities.Group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer>, GroupCustomRepository{
    Page<Group> findAllByAdminId(int adminId, Pageable pageable);
    Group findFirstByOrderByIdDesc();
    long countByAdminId(int adminId);
}