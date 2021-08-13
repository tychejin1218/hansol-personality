package com.eduhansol.app.services;

import com.eduhansol.app.entities.TesterResult;

public interface TesterResultService {
    TesterResult findByTesterid(int testerId);
    void save(TesterResult entity);
    void deleteByTesterId(int testerId);
}