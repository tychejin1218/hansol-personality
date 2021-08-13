package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Department;
import com.eduhansol.app.repositories.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository _departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        _departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getList() {
        return _departmentRepository.findByIsDelete(false);
    }

    @Override
    public List<Department> getList(int regionId) {
        return _departmentRepository.findByRegionIdAndIsDelete(regionId, false);
    }

    @Override
    public Department get(int id) {
        return _departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Department entity) {
        _departmentRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        Department entity = _departmentRepository.findById(id).orElse(null);
        if(entity != null) _departmentRepository.delete(entity);
    }
}