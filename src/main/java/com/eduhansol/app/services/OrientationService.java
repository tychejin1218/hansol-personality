package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Orientation;

public interface OrientationService{
    List<Orientation> getList(int normPersonalityId);
    Orientation get(int normPersonalityId, int page);
}