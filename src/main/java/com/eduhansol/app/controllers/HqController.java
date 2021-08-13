package com.eduhansol.app.controllers;

import java.util.List;

import javax.validation.Valid;

import com.eduhansol.app.entities.Hq;
import com.eduhansol.app.services.HqService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HqController {
    private final HqService _hqService;

    public HqController(HqService hqService) {
        _hqService = hqService;
    }

    @GetMapping("/hq/index")
    public String index(Model model) {
        List<Hq> list = _hqService.getList();
        model.addAttribute("list", list);
        return "hq/index";
    }

    @GetMapping("/hq/post")
    public String post(@ModelAttribute Hq hq, Model model) {
        model.addAttribute("hq", hq);
        return "hq/post";
    }

    @PostMapping("/hq/post")
    public String post(@ModelAttribute @Valid Hq hq, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            hq.setName(hq.getName().trim());
            _hqService.save(hq);
            return "redirect:/hq/index";
        }
        model.addAttribute("hq", hq);
        return "hq/post";
    }

    @GetMapping("/hq/update")
    public String update(int id, Model model) {
        Hq hq = _hqService.get(id);
        model.addAttribute("hq", hq);
        return "hq/update";
    }

    @PostMapping("/hq/update")
    public String update(@ModelAttribute @Valid Hq hq, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Hq entity = _hqService.get(hq.getId());
            if (entity == null)
                return "redirect:/error";
            entity.setName(hq.getName().trim());
            _hqService.save(entity);
            return "redirect:/hq/index";
        }
        model.addAttribute("hq", hq);
        return "hq/update";
    }

    @PostMapping("/hq/delete")
    public String delete(int id) {
        _hqService.delete(id);
        return "redirect:/hq/index";
    }
}