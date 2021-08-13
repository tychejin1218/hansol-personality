package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Region;

public interface RegionService{
    List<Region> getList();
    List<Region> getList(int hqId);
    Region get(int id);
    void save(Region entity);
    void delete(int id);
}