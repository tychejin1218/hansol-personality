package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Tester;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TesterService{
    List<Tester> getList(int groupId);
    Page<Tester> getList(int groupId, Pageable pageable);
    Page<Tester> getList(int groupId, String name, Pageable pageable);

    Tester get(int id);
    Tester get(String name, String authKey);
    Tester get(String applyId);
    
    int getCount(int groupId);
    int getCount(int groupId, int testState);
    int getCount(int groupId, int testState, String name);
    int getCount(String applyId);

    Tester post(Tester model);
    void update(Tester model);
    void delete(int id);
}