package com.eduhansol.app.services;

import com.eduhansol.app.entities.Info;

public interface InfoService {
    Info findById(int id);
    void save(Info info);
}