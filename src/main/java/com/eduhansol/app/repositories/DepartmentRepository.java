package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    List<Department> findByIsDelete(boolean isDelete);
    List<Department> findByRegionId(int regionId);
    List<Department> findByRegionIdAndIsDelete(int regionId, boolean isDelete);
}