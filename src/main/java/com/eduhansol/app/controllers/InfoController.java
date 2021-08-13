package com.eduhansol.app.controllers;

import javax.validation.Valid;

import com.eduhansol.app.entities.Info;
import com.eduhansol.app.services.InfoService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InfoController {
    private final InfoService infoService;

    public InfoController(InfoService infoService){
        this.infoService = infoService;
    }

    @GetMapping("/info/update")
    public String update(boolean confirmed, Model model){
        Info info = this.infoService.findById(1);
        model.addAttribute("info", info);
        model.addAttribute("confirmed", confirmed);
        return "info/update";
    }

    @PostMapping("/info/update")
    public String update(@Valid Info info, BindingResult bindResult, Model model){
        if(!bindResult.hasErrors()){
            Info entity = this.infoService.findById(1);
            if(entity == null)
                return "redirect:error";
            entity.setTel(info.getTel());
            this.infoService.save(entity);
            return "redirect:/info/update?confirmed=true";
        }
        model.addAttribute("info", info);
        return "info/update";
    }
}