package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Capability;
import com.eduhansol.app.repositories.CapabilityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapabilityServiceImpl implements CapabilityService {

    private final CapabilityRepository _capabilityRepository;

    @Autowired
    public CapabilityServiceImpl(CapabilityRepository capabilityRepository) {
        _capabilityRepository = capabilityRepository;
    }

    @Override
    public List<Capability> getList(int normPersonalityId) {
        return _capabilityRepository.findByNormPersonalityId(normPersonalityId);
    }
}