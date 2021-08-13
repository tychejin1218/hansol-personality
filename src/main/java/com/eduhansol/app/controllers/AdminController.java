package com.eduhansol.app.controllers;

import java.util.regex.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.eduhansol.app.entities.Admin;
import com.eduhansol.app.entities.Info;
import com.eduhansol.app.services.AdminService;
import com.eduhansol.app.services.InfoService;
import com.eduhansol.app.viewmodels.ResetPasswordViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {

    private final AdminService _adminService;
    private final InfoService infoService;

    @Autowired
    public AdminController(AdminService adminService, InfoService infoService) {
        _adminService = adminService;
        this.infoService = infoService;
    }

    @GetMapping("/admin/login")
    public String loginAdmin(CsrfToken csrfToken, Model model, @RequestParam @Nullable String error) {
        Info info = this.infoService.findById(1);
        if (info != null)
            model.addAttribute("info", info);
        model.addAttribute("_csrf", csrfToken);
        model.addAttribute("error", error);
        return "/admin/login";
    }

    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/admin/login";
    }

    @GetMapping("/admin/resetPassword")
    public String resetPassword(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        Admin admin = _adminService.getLogin(username);

        if (admin == null) {
            return "/admin/login";
        }
        return "/admin/resetPassword";
    }

    @PostMapping("/admin/resetPassword")
    public String resetPassword(@Valid ResetPasswordViewModel resetPasswordViewModel, BindingResult bindingResult,
            Model model) {

        if (!bindingResult.hasErrors()) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();

            Admin admin = _adminService.getLogin(username);

            if (admin == null) {
                model.addAttribute("errorMessage", "존재하지 않는 관리자 입니다.");
                model.addAttribute("resetPasswordViewModel", resetPasswordViewModel);
                return "/admin/resetPassword";
            }

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean isMatchPassword = bCryptPasswordEncoder.matches(resetPasswordViewModel.getCurrentPassword(),
                    admin.getPassword());

            if (!isMatchPassword) {
                model.addAttribute("errorMessage", "현재 비밀번호가 일치하지 않습니다.");
                model.addAttribute("resetPasswordViewModel", resetPasswordViewModel);
                return "/admin/resetPassword";
            }
            
            Pattern pattern = Pattern.compile("([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])");
            Matcher matcher = pattern.matcher(resetPasswordViewModel.getNewPasswordConfirm());

            if(!matcher.find() || resetPasswordViewModel.getNewPasswordConfirm().length() < 8){
                model.addAttribute("errorMessage", "새 비밀번호는 영문자, 숫자, 특수 문자를 포함한 8자리 이상만 설정 가능합니다.");
                model.addAttribute("resetPasswordViewModel", resetPasswordViewModel);
                return "/admin/resetPassword";
            }

            boolean isMatchNewPassword = resetPasswordViewModel.getNewPassword()
                    .equals(resetPasswordViewModel.getNewPasswordConfirm());

            if (!isMatchNewPassword) {
                model.addAttribute("errorMessage", "새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
                model.addAttribute("resetPasswordViewModel", resetPasswordViewModel);
                return "/admin/resetPassword";
            }

            admin.setPassword(resetPasswordViewModel.getNewPasswordConfirm());
            admin.setIsFirstLogin(false);
            _adminService.update(admin);

            return "redirect:/group/index";
        }
        model.addAttribute("resetPasswordViewModel", resetPasswordViewModel);
        return "/admin/resetPassword";
    }

}
