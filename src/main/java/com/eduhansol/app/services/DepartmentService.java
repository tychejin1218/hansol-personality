package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Department;

public interface DepartmentService {
    List<Department> getList();
    List<Department> getList(int regionId);
    Department get(int id);
    void save(Department entity);
    void delete(int id);
}