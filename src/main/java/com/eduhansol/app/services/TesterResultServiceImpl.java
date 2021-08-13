package com.eduhansol.app.services;

import javax.transaction.Transactional;

import com.eduhansol.app.entities.TesterResult;
import com.eduhansol.app.repositories.TesterResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TesterResultServiceImpl implements TesterResultService {

    private final TesterResultRepository _testerResultRepository;

    @Autowired
    public TesterResultServiceImpl(TesterResultRepository testerResultRepository) {
        _testerResultRepository = testerResultRepository;
    }

    @Override
    public TesterResult findByTesterid(int testerId) {
        return _testerResultRepository.findByTesterId(testerId);
    }

    @Override
    public void save(TesterResult entity) {
        _testerResultRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteByTesterId(int testerId) {
        _testerResultRepository.deleteByTesterId(testerId);
    }
}