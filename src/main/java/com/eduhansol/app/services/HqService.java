package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Hq;

public interface HqService {
    List<Hq> getList();
    Hq get(int id);
    void save(Hq entity);
    void delete(int id);
}