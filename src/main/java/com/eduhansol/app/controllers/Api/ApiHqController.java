
package com.eduhansol.app.controllers.Api;

import java.util.List;

import com.eduhansol.app.entities.Hq;
import com.eduhansol.app.services.HqService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiHqController {
    private final HqService _hqService;

    public ApiHqController(HqService hqService) {
        _hqService = hqService;
    }

    @GetMapping("/api/hqs")
    public List<Hq> getList() {
        List<Hq> hqs = _hqService.getList();
        return hqs;
    }

    @GetMapping("/api/hq")
    public Hq get(int id) {
        Hq hq = _hqService.get(id);
        return hq;
    }
}