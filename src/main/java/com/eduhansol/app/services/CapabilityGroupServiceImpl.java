package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.CapabilityGroup;
import com.eduhansol.app.repositories.CapabilityGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapabilityGroupServiceImpl implements CapabilityGroupService {

    private final CapabilityGroupRepository _capabilityGroupRepository;

    @Autowired
    public CapabilityGroupServiceImpl(CapabilityGroupRepository capabilityGroupRepository) {
        _capabilityGroupRepository = capabilityGroupRepository;
    }

    @Override
    public List<CapabilityGroup> getList(int normPersonalityId) {
        return _capabilityGroupRepository.findByNormPersonalityId(normPersonalityId);
    }

}