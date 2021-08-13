package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Hq;
import com.eduhansol.app.repositories.HqRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HqServiceImpl implements HqService {

    private final HqRepository _hqRepository;

    @Autowired
    public HqServiceImpl(HqRepository hqRepository) {
        _hqRepository = hqRepository;
    }

    @Override
    public List<Hq> getList() {
        return _hqRepository.findByIsDelete(false);
    }

    @Override
    public Hq get(int id) {
        return _hqRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Hq entity) {
        _hqRepository.save(entity);
    }

    @Override
    public void delete(int id) {
        Hq entity = _hqRepository.findById(id).orElse(null);
        if(entity != null) _hqRepository.delete(entity);
    }
}