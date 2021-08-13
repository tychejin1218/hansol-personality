package com.eduhansol.app.services;

import com.eduhansol.app.entities.Mark;
import com.eduhansol.app.repositories.MarkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkServiceImpl implements MarkService {

    private final MarkRepository _markRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository) {
        _markRepository = markRepository;
    }

    @Override
    public Mark get(int testerId) {
        return _markRepository.findByTesterId(testerId);
    }

    @Override
    public Mark post(Mark model) {
        return _markRepository.save(model);
    }

    @Override
    public void delete(int testerId) {

        Mark mark = _markRepository.findByTesterId(testerId);
        if(mark == null) return;
        _markRepository.delete(mark);
    }
}