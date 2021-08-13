package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.TempMark1;

public interface TempMark1Service {
    List<TempMark1> getList(int testerId);
    List<TempMark1> getList(int testerId, int pageNo);
    TempMark1 get(int testerId, int questionId);
    TempMark1 findFirstByTesterIdOrderByQuestionIdDesc(int testerId);
    TempMark1 post(TempMark1 model);
    TempMark1 update(TempMark1 model);
    void delete(int testerId);
}