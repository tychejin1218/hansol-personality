package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Tester;
import com.eduhansol.app.repositories.TesterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TesterServiceImpl implements TesterService {

    private final TesterRepository _testerRepository;

    @Autowired
    public TesterServiceImpl(TesterRepository testerRepository) {
        _testerRepository = testerRepository;
    }

    @Override 
    public List<Tester> getList(int groupId){
        return _testerRepository.findByGroupId(groupId);
    }

    @Override
    public Page<Tester> getList(int groupId, Pageable pageable) {
        return _testerRepository.findByGroupId(groupId, pageable);
    }

    @Override
    public Page<Tester> getList(int groupId, String name, Pageable pageable) {
        return _testerRepository.findByGroupIdAndName(groupId, name, pageable);
    }

    public Tester get(String applyId) {
        return _testerRepository.findByApplyId(applyId);
    }

    @Override
    public Tester get(int id) {
        return _testerRepository.findById(id).get();
    }

    @Override
    public Tester get(String name, String authKey) {
        return _testerRepository.findByNameAndAuthKey(name, authKey);
    }

    @Override
    public int getCount(int groupId) {
        return (int) _testerRepository.countByGroupId(groupId);
    }

    @Override
    public int getCount(int groupId, int testState) {
        return (int) _testerRepository.countByGroupIdAndTestState(groupId, testState);
    }

    @Override
    public int getCount(int groupId, int testState, String name) {
        return (int) _testerRepository.countByGroupIdAndTestStateAndName(groupId, testState, name);
    }

    @Override
    public int getCount(String applyId) {
        return (int) _testerRepository.countByApplyId(applyId);
    }

    @Override
    public Tester post(Tester model) {
        return _testerRepository.save(model);
    }

    @Override
    public void update(Tester model) {
        _testerRepository.save(model);
    }

    @Override
    public void delete(int id) {
        Tester tester = _testerRepository.findById(id).get();
        if (tester == null)
            return;
        _testerRepository.delete(tester);
    }
}