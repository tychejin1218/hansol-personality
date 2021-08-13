package com.eduhansol.app.services;

import java.util.List;

import javax.transaction.Transactional;

import com.eduhansol.app.entities.TempMark2;
import com.eduhansol.app.repositories.TempMark2Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TempMark2ServiceImpl implements TempMark2Service {

    private final TempMark2Repository _tempMark2Repository;

    @Autowired
    public TempMark2ServiceImpl(TempMark2Repository tempMark2Repository) {
        _tempMark2Repository = tempMark2Repository;
    }

    @Override
    public List<TempMark2> getList(int testerId) {
        return _tempMark2Repository.findByTesterIdOrderByQuestionIdAsc(testerId);
    }

    @Override
    public List<TempMark2> getList(int testerId, int pageNo) {
        return _tempMark2Repository.findByTesterIdAndPageNoOrderByQuestionIdAsc(testerId, pageNo);
    }

    @Override
    public TempMark2 get(int testerId, int questionSetId, String mark2) {
        List<TempMark2> tempMark2s = _tempMark2Repository.findByTesterIdAndQuestionSetIdAndMark2(testerId, questionSetId, mark2);
        if(tempMark2s.size() > 0){
            return tempMark2s.get(0);
        }
        return null;
    }

    @Override
    public TempMark2 getByQuestionId(int testerId, int questionId) {
        return _tempMark2Repository.findByTesterIdAndQuestionId(testerId, questionId);
    }

    @Override
    public TempMark2 getByQuestionId(int testerId, int questionId, String mark2) {
        return _tempMark2Repository.findByTesterIdAndQuestionIdAndMark2(testerId, questionId, mark2);
    }

    @Override
    public TempMark2 post(TempMark2 model) {
        return _tempMark2Repository.save(model);
    }

    @Override
    public void update(TempMark2 model) {
        _tempMark2Repository.save(model);
    }

    @Override
    public void update(int testerId, int questionSetId, int questionId, String mark2) {
        List<TempMark2> tempMark2s = _tempMark2Repository.findByTesterIdAndQuestionSetIdAndMark2(testerId, questionSetId, mark2);

        for(TempMark2 tempMark2 : tempMark2s){
            tempMark2.setMark2("");
            _tempMark2Repository.save(tempMark2);
        }

        String convertMark2 = "";

        if(mark2 == "1"){
            convertMark2 = "2";
        }else{
            convertMark2 = "1";
        }

        List<TempMark2> convertTempMark2s = _tempMark2Repository.findByTesterIdAndQuestionSetIdAndMark2(testerId, questionSetId, convertMark2);

        for(TempMark2 tempMark2 : convertTempMark2s){
            if(questionId == tempMark2.getQuestionId()){
                tempMark2.setMark2("");
                _tempMark2Repository.save(tempMark2);
            }
        }
    }

    @Override
    public void delete(int testerId) {
        List<TempMark2> tempMark2s = _tempMark2Repository.findByTesterIdOrderByQuestionIdAsc(testerId);
        if (tempMark2s.size() <= 0)
            return;
        _tempMark2Repository.deleteByTesterId(testerId);
    }

    @Override
    @Transactional
    public void delete(int testerId, int questionSetId) {
        _tempMark2Repository.deleteByTesterIdAndQuestionSetId(testerId, questionSetId);
    }

    @Override
    @Transactional
    public void delete(int testerId, int questionSetId, String mark2) {
        _tempMark2Repository.deleteByTesterIdAndQuestionSetIdAndMark2(testerId, questionSetId, mark2);
    }

    @Override
    @Transactional
    public void deleteByQuestionId(int testerId, int questionId) {
        _tempMark2Repository.deleteByTesterIdAndQuestionId(testerId, questionId);
    }

   

  

   

   
}