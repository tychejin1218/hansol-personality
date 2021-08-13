package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Factor;

public interface FactorService {
    List<Factor> getList(int normPersonalityId);
}