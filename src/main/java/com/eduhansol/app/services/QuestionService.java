package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Question;

public interface QuestionService {
    List<Question> getList(int normPersonalityId);
    List<Question> getList(int normPersonalityId, int page);

    Question get(int id);
}