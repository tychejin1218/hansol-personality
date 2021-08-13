package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Narrative;
import com.eduhansol.app.repositories.NarrativeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NarrativeServiceImpl implements NarrativeService {
    private final NarrativeRepository _narrativeRepository;

    @Autowired
    public NarrativeServiceImpl(NarrativeRepository narrativeRepository) {
        _narrativeRepository = narrativeRepository;
    }

    @Override
    public List<Narrative> getList() {
        return _narrativeRepository.findAll();
    }

    @Override
    public List<Narrative> getList(int subFactorId) {
        return _narrativeRepository.findBySubFactorId(subFactorId);
    }

    @Override
    public Narrative get(int id) {
        return _narrativeRepository.findById(id).get();
    }

}