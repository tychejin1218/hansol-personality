package com.eduhansol.app.controllers.Api;

import java.util.List;

import com.eduhansol.app.entities.Region;
import com.eduhansol.app.services.RegionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiRegionController {
    private final RegionService _regionService;

    public ApiRegionController(RegionService regionService) {
        _regionService = regionService;
    }

    @GetMapping("/api/regions")
    public List<Region> getList(int hqId) {
        List<Region> regions = _regionService.getList(hqId);
        return regions;
    }

    @GetMapping("/api/region")
    public Region get(int id) {
        Region region = _regionService.get(id);
        return region;
    }
}