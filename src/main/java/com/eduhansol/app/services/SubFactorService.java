package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.SubFactor;

public interface SubFactorService {
    List<SubFactor> getList(int normPersonalityId);
    SubFactor get(int id);
    void save(SubFactor subFactor);
}
