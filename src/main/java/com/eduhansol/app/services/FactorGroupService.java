package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.FactorGroup;

public interface FactorGroupService {
    List<FactorGroup> getList(int normPersonalityId);
}