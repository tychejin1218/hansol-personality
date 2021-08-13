package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Tester;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterRepository extends JpaRepository<Tester, Integer>{
    List<Tester> findByGroupId(int groupId);
    Page<Tester> findByGroupId(int groupId, Pageable pageable);
    Page<Tester> findByGroupIdAndName(int groupId, String name, Pageable pageable);
    Tester findByNameAndAuthKey(String name, String authKey);
    Tester findByApplyId(String applyId);
    long countByGroupId(int groupId);
    long countByGroupIdAndTestState(int groupId, int testState);
    long countByGroupIdAndTestStateAndName(int groupId, int testState, String name);
    long countByApplyId(String applyId);
}