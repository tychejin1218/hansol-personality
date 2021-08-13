package com.eduhansol.app.services;

import java.util.List;
import com.eduhansol.app.entities.CapabilityGroup;

public interface CapabilityGroupService{
    List<CapabilityGroup> getList(int normPersonalityId);
}