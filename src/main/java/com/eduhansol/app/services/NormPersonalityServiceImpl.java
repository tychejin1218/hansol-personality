package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.NormPersonality;
import com.eduhansol.app.repositories.NormPersonalityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NormPersonalityServiceImpl implements NormPersonalityService {

    private final NormPersonalityRepository _normPersonalityRepository;

    @Autowired
    public NormPersonalityServiceImpl(NormPersonalityRepository normPersonalityRepository) {
        _normPersonalityRepository = normPersonalityRepository;
    }

    @Override
    public List<NormPersonality> getList() {
        return _normPersonalityRepository.findAll();
    }

    @Override
    public NormPersonality get(int id) {
        return _normPersonalityRepository.findById(id).get();
    }
}