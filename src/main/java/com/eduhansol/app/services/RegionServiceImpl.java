package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Region;
import com.eduhansol.app.repositories.RegionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository _regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        _regionRepository = regionRepository;
    }

    @Override
    public List<Region> getList() {
        return _regionRepository.findAll();
    }

    @Override
    public List<Region> getList(int hqId) {
        return _regionRepository.findByHqIdAndIsDelete(hqId, false);
    }

    @Override
    public Region get(int id) {
        return _regionRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Region entity) {
        _regionRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        Region entity = _regionRepository.findById(id).orElse(null);
        if(entity != null){
            _regionRepository.delete(entity);
        }
    }

}