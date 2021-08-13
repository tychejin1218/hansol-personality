package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Narrative;

public interface NarrativeService {
    List<Narrative> getList();
    List<Narrative> getList(int subFactorId);
    Narrative get(int id);
}