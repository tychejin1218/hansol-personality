package com.eduhansol.app.controllers;

import java.util.List;

import javax.validation.Valid;

import com.eduhansol.app.entities.Department;
import com.eduhansol.app.entities.Region;
import com.eduhansol.app.services.DepartmentService;
import com.eduhansol.app.services.RegionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DepartmentController {
    private final DepartmentService _departmentService;
    private final RegionService _regionService;

    public DepartmentController(DepartmentService departmentService, RegionService regionService) {
        _departmentService = departmentService;
        _regionService = regionService;
    }

    @GetMapping("/department/index")
    public String index(int regionId, Model model){
        List<Department> list = _departmentService.getList(regionId);
        Region region = _regionService.get(regionId);
        model.addAttribute("list", list);
        model.addAttribute("regionId", regionId);
        model.addAttribute("hqId", region.getHq().getId());
        return "department/index";
    }

    @GetMapping("/department/post")
    public String post(int regionId, @ModelAttribute Department department, Model model){
        Region region = _regionService.get(regionId);
        department.setRegion(region);
        model.addAttribute("regionId", regionId);
        model.addAttribute("department", department);
        return "department/post";
    }

    @PostMapping("/department/post")
    public String post(@ModelAttribute @Valid Department department, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            _departmentService.save(department);
            return "redirect:/department/index?regionId=" + department.getRegion().getId();
        }
        model.addAttribute("department", department);
        model.addAttribute("regionId", department.getRegion().getId());
        return "department/post";
    }

    @GetMapping("/department/update")
    public String update(int id, Model model){
        Department entity = _departmentService.get(id);
        model.addAttribute("department", entity);
        model.addAttribute("regionId", entity.getRegion().getId());
        return "department/update";
    }
    
    @PostMapping("/department/update")
    public String update(@ModelAttribute @Valid Department department, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            Department entity = _departmentService.get(department.getId());
            if(entity == null) return "redirect:/error";
            entity.setRegion(department.getRegion());
            entity.setName(department.getName().trim());

            _departmentService.save(entity);
            return "redirect:/department/index?regionId=" + entity.getRegion().getId();
        }
        model.addAttribute("department", department);
        model.addAttribute("regionId", department.getRegion().getId());
        return "department/update";
    }

    @PostMapping("/department/delete")
    public String delete(int id, int regionId){

        Department department = _departmentService.get(id);
        if(department != null){
            department.setIsDelete(true);
            _departmentService.save(department);
        }
        return "redirect:/department/index?regionId=" + regionId;
    }
}