package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.TempMark1;
import com.eduhansol.app.repositories.TempMark1Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempMark1ServiceImpl implements TempMark1Service {

    private final TempMark1Repository _tempMark1Repository;

    @Autowired
    public TempMark1ServiceImpl(TempMark1Repository tempMark1Repository) {
        _tempMark1Repository = tempMark1Repository;
    }

    @Override
    public List<TempMark1> getList(int testerId) {
        return _tempMark1Repository.findByTesterIdOrderByQuestionIdAsc(testerId);
    }

    @Override
    public List<TempMark1> getList(int testerId, int pageNo) {
        return _tempMark1Repository.findByTesterIdAndPageNoOrderByQuestionIdAsc(testerId, pageNo);
    }

    @Override
    public TempMark1 get(int testerId, int questionId) {
        return _tempMark1Repository.findByTesterIdAndQuestionId(testerId, questionId);
    }

    @Override
    public TempMark1 findFirstByTesterIdOrderByQuestionIdDesc(int testerId) {
        return _tempMark1Repository.findFirstByTesterIdOrderByQuestionIdDesc(testerId);
    }

    @Override
    public TempMark1 post(TempMark1 model) {
        return _tempMark1Repository.save(model);
    }

    @Override
    public TempMark1 update(TempMark1 model) {
        return _tempMark1Repository.save(model);
    }

    @Override
    public void delete(int testerId) {
        List<TempMark1> tempMark1s = _tempMark1Repository.findByTesterIdOrderByQuestionIdAsc(testerId);
        if (tempMark1s.size() <= 0)
            return;
        _tempMark1Repository.deleteByTesterId(testerId);
    }
}