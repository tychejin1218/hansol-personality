package com.eduhansol.app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.eduhansol.app.configs.Constants;
import com.eduhansol.app.entities.Admin;
import com.eduhansol.app.entities.Department;
import com.eduhansol.app.entities.Hq;
import com.eduhansol.app.entities.Region;
import com.eduhansol.app.entities.Role;
import com.eduhansol.app.services.AdminService;
import com.eduhansol.app.services.DepartmentService;
import com.eduhansol.app.services.HqService;
import com.eduhansol.app.services.RegionService;
import com.eduhansol.app.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final AdminService _adminService;
    private final RoleService _roleService;
    private final HqService _hqService;
    private final RegionService _regionService;
    private final DepartmentService _departmentService;

    @Autowired
    public UserController(AdminService adminService, RoleService roleService, HqService hqService,
            RegionService regionService, DepartmentService departmentService) {
        _adminService = adminService;
        _roleService = roleService;
        _hqService = hqService;
        _regionService = regionService;
        _departmentService = departmentService;
    }

    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
        @RequestParam(name = "row", defaultValue = Constants.ROW_STR) int row) {
        Pageable pageable = PageRequest.of(pageNo - 1, row, Direction.DESC, "id");
        List<Admin> list = _adminService.getListByIsDelete(pageable).getContent();
        int count = _adminService.getCountByIsDelete(false);

        int index = count - (pageNo - 1) * row;
        int totalPageNo = (count + (row - 1)) / row;
        if(totalPageNo < pageNo){
            return "redirect:/user/index?pageNo=1&row=" + row;
        }

        int startPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            startPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT - Constants.PAGE_COUNT + 1;
        } else {
            startPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT + 1;
        }

        int endPageNo = startPageNo + Constants.PAGE_COUNT - 1;
        if (endPageNo > totalPageNo)
            endPageNo = totalPageNo;

        int previusPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            previusPageNo = (pageNo / Constants.PAGE_COUNT - 1) * Constants.PAGE_COUNT;
        } else {
            previusPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT;
        }

        int nextPageNo = 1;
        if (pageNo % Constants.PAGE_COUNT == 0) {
            nextPageNo = pageNo / Constants.PAGE_COUNT * Constants.PAGE_COUNT + 1;
        } else {
            nextPageNo = (pageNo / Constants.PAGE_COUNT + 1) * Constants.PAGE_COUNT + 1;
        }

        model.addAttribute("list", list);
        model.addAttribute("index", index);

        model.addAttribute("pageCount", Constants.PAGE_COUNT);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPageNo", totalPageNo);
        model.addAttribute("startPageNo", startPageNo);
        model.addAttribute("endPageNo", endPageNo);
        model.addAttribute("previusPageNo", previusPageNo);
        model.addAttribute("nextPageNo", nextPageNo);
        model.addAttribute("row", row);
        return "user/index";
    }

    @GetMapping("/user/post")
    public String post(@ModelAttribute Admin admin, Model model) {
        List<Role> roles = _roleService.getList();
        List<Hq> hqs = _hqService.getList();
        List<Region> regions = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        if (hqs.size() > 0) {
            regions = _regionService.getList(hqs.get(0).getId());
        }

        if (regions.size() > 0) {
            departments = _departmentService.getList(regions.get(0).getId());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("hqs", hqs);
        model.addAttribute("regions", regions);
        model.addAttribute("departments", departments);
        return "user/post";
    }

    @PostMapping("/user/post")
    public String post(@ModelAttribute @Valid Admin admin, BindingResult bindingResult, Model model) {

        String uppderEmail = admin.getEmail().trim().toUpperCase();

        Admin existAdmin = _adminService.getLogin(uppderEmail);

        if(existAdmin != null){
            bindingResult.addError(new ObjectError("existAdminName", "이미 등록된 관리자 ID입니다."));
            model.addAttribute("existAdminName", "이미 등록된 관리자 ID입니다.");
        }

        if (!bindingResult.hasErrors()) {

            admin.setEmail(uppderEmail);

            if (admin.getName() == null) {
                admin.setName("");
            }

            if (admin.getPhone() == null) {
                admin.setPhone("");
            }

            if (admin.getTel() == null) {
                admin.setTel("");
            }
            admin.setIsDelete(false);
            _adminService.post(admin);
            return "redirect:/user/index";
        }

        List<Role> roles = _roleService.getList();
        List<Hq> hqs = _hqService.getList();
        List<Region> regions = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        if (hqs.size() > 0) {
            regions = _regionService.getList(hqs.get(0).getId());
        }

        if (regions.size() > 0) {
            departments = _departmentService.getList(regions.get(0).getId());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("hqs", hqs);
        model.addAttribute("regions", regions);
        model.addAttribute("departments", departments);
        return "user/post";
    }

    @GetMapping("/user/update")
    public String update(int id, Model model) {
        Admin admin = _adminService.get(id);
        List<Role> roles = _roleService.getList();
        List<Hq> hqs = _hqService.getList();
        List<Region> regions = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        if (hqs.size() > 0) {
            // regions = _regionService.getList(hqs.get(0).getId());
            regions = _regionService.getList(admin.getDepartment().getRegion().getHq().getId());
        }

        if (regions.size() > 0) {
            departments = _departmentService.getList(admin.getDepartment().getRegion().getId());
        }
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);
        model.addAttribute("hqs", hqs);
        model.addAttribute("regions", regions);
        model.addAttribute("departments", departments);
        return "user/update";
    }

    @PostMapping("/user/update")
    public String update(@ModelAttribute @Valid Admin admin, BindingResult bindingResult, Model model) {
        Admin entity = _adminService.get(admin.getId());
        if (entity == null)
            return "redirect:/error";

        if (!bindingResult.hasErrors()) {
            String uppderEmail = admin.getEmail().trim().toUpperCase();
            admin.setEmail(uppderEmail);

            if (admin.getName() == null) {
                entity.setName("");
            }

            if (admin.getPhone() == null) {
                entity.setPhone("");
            }

            if (admin.getTel() == null) {
                entity.setTel("");
            }

            //entity.setPassword(admin.getPassword());
            entity.setEmail(admin.getEmail());
            entity.setRole(admin.getRole());
            entity.setDepartment(admin.getDepartment());
            _adminService.update(entity);
            return "redirect:/user/index";
        }

        List<Role> roles = _roleService.getList();
        List<Hq> hqs = _hqService.getList();
        List<Region> regions = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        if (hqs.size() > 0) {
            regions = _regionService.getList(admin.getDepartment().getRegion().getHq().getId());
        }

        if (regions.size() > 0) {
            departments = _departmentService.getList(admin.getDepartment().getRegion().getId());
        }
        model.addAttribute("admin", admin);
        model.addAttribute("roles", roles);
        model.addAttribute("hqs", hqs);
        model.addAttribute("regions", regions);
        model.addAttribute("departments", departments);
        return "user/update";
    }

    @PostMapping("/user/delete")
    public String delete(int id) {
        Admin admin = _adminService.get(id);

        if(admin != null){
            admin.setIsDelete(true);
            _adminService.update(admin);
        }
        return "redirect:/user/index";
    }

    @GetMapping("/user/resetPassword")
    public String resetPassword(int id) {
        Admin admin = _adminService.get(id);
        if (admin == null)
            return "redirect:/user/index";

        String password = "hansol" + LocalDateTime.now().getYear();
        admin.setPassword(password);
        admin.setIsFirstLogin(true);
        _adminService.update(admin);
        return "redirect:/user/index";
    }
}