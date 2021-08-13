package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.FactorGroup;
import com.eduhansol.app.repositories.FactorGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FactorGroupServiceImpl implements FactorGroupService {
    
    private final FactorGroupRepository _factorGroupRepository;

    @Autowired
    public FactorGroupServiceImpl(FactorGroupRepository factorGroupRepository) {
        _factorGroupRepository = factorGroupRepository;
    }

    @Override
    public List<FactorGroup> getList(int normPersonalityId) {
        return _factorGroupRepository.findByNormPersonalityId(normPersonalityId);
    }
}