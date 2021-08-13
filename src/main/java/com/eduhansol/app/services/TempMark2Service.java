package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.TempMark2;

public interface TempMark2Service {
    List<TempMark2> getList(int testerId);
    List<TempMark2> getList(int testerId, int pageNo);
    TempMark2 get(int testerId, int questionSetId, String mark2);
    TempMark2 getByQuestionId(int testerId, int questionId);
    TempMark2 getByQuestionId(int testerId, int questionId, String mark2);
    TempMark2 post(TempMark2 model);
    void update(TempMark2 model);
    void update(int testerId, int questionSetId, int questionId, String mark2);
    void delete(int testerId);
    void delete(int testerId, int questionSetId);
    void delete(int testerId, int questionSetId, String mark2);
    void deleteByQuestionId(int testerId, int questionId);
}