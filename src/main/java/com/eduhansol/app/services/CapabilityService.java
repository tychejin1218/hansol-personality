package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Capability;

public interface CapabilityService{
    List<Capability> getList(int normPersonalityId);
}