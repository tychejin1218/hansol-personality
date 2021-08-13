package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Factor;
import com.eduhansol.app.repositories.FactorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactorServiceImpl implements FactorService {

    private final FactorRepository _factorRepository;

    @Autowired
    public FactorServiceImpl(FactorRepository factorRepository) {
        _factorRepository = factorRepository;
    }

    @Override
    public List<Factor> getList(int normPersonalityId) {
        return _factorRepository.findByNormPersonalityId(normPersonalityId);
    }

}