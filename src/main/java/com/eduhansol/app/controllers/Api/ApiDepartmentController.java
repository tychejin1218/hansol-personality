package com.eduhansol.app.controllers.Api;

import java.util.List;

import com.eduhansol.app.entities.Department;
import com.eduhansol.app.services.DepartmentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiDepartmentController{
    private final DepartmentService _departmentService;

    public ApiDepartmentController(DepartmentService departmentService) {
        _departmentService = departmentService;
    }

    @GetMapping("/api/departments")
    public List<Department> getList(int regionId){
        List<Department> departments = _departmentService.getList(regionId);
        return departments;
    }

    @GetMapping("/api/department")
    public Department get(int id){
        Department department = _departmentService.get(id);
        return department;
    }
}