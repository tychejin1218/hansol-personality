package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Question;
import com.eduhansol.app.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository _questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        _questionRepository = questionRepository;
    }

    @Override
    public List<Question> getList(int normPersonalityId) {
        return _questionRepository.findByNormPersonalityId(normPersonalityId);
    }

    @Override
    public List<Question> getList(int normPersonalityId, int pageNo) {
        return _questionRepository.findByNormPersonalityIdAndPageNo(normPersonalityId, pageNo);
    }

    @Override
    public Question get(int id) {
        return _questionRepository.findById(id).get();
    }
}