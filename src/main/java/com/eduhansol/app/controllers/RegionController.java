package com.eduhansol.app.controllers;

import java.util.List;

import javax.validation.Valid;

import com.eduhansol.app.entities.Hq;
import com.eduhansol.app.entities.Region;
import com.eduhansol.app.services.HqService;
import com.eduhansol.app.services.RegionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegionController {
    private final RegionService _regionService;
    private final HqService _hqService;

    public RegionController(RegionService regionService, HqService hqService) {
        _regionService = regionService;
        _hqService = hqService;
    }

    @GetMapping("/region/index")
    public String index(int hqId, Model model) {
        List<Region> list = _regionService.getList(hqId);
        model.addAttribute("list", list);
        model.addAttribute("hqId", hqId);
        return "region/index";
    }

    @GetMapping("/region/post")
    public String post(@ModelAttribute Region region, int hqId, Model model) {
        Hq hq = _hqService.get(hqId);
        region.setHq(hq);
        model.addAttribute("region", region);
        model.addAttribute("hqId", hqId);
        return "region/post";
    }

    @PostMapping("/region/post")
    public String post(@ModelAttribute @Valid Region region, Model model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            _regionService.save(region);
            return "redirect:/region/index?hqId=" + region.getHq().getId();
        }
        model.addAttribute("hqId", region.getHq().getId());
        model.addAttribute("region", region);
        return "region/post";
    }

    @GetMapping("/region/update")
    public String update(int id, Model model) {
        Region entity = _regionService.get(id);
        model.addAttribute("region", entity);
        model.addAttribute("hqId", entity.getHq().getId());
        return "region/update";
    }

    @PostMapping("/region/update")
    public String update(@ModelAttribute @Valid Region region, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Region entity = _regionService.get(region.getId());
            if (entity == null)
                return "redirect:/error";
            entity.setHq(region.getHq());
            entity.setName(region.getName().trim());
            _regionService.save(entity);
            return "redirect:/region/index?hqId=" + entity.getHq().getId();
        }
        model.addAttribute("hqId", region.getHq().getId());
        model.addAttribute("region", region);
        return "region/update";
    }

    @PostMapping("/region/delete")
    public String delete(int id, int hqId) {
        Region region = _regionService.get(id);

        if(region != null){
            region.setIsDelete(true);
            _regionService.save(region);
        }
        return "redirect:/region/index?hqId=" + hqId;
    }
}