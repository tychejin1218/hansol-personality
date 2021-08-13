package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.SubFactor;
import com.eduhansol.app.repositories.SubFactorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubFactorServiceImpl implements SubFactorService {
    private final SubFactorRepository _subFactorRepository;

    @Autowired
    public SubFactorServiceImpl(SubFactorRepository subFactorRepository) {
        _subFactorRepository = subFactorRepository;
    }

    @Override
    public List<SubFactor> getList(int normPersonalityId) {
        return _subFactorRepository.findByNormPersonalityId(normPersonalityId);
    }

    @Override
    public SubFactor get(int id) {
        return _subFactorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SubFactor subFactor) {
        _subFactorRepository.save(subFactor);
    }
}