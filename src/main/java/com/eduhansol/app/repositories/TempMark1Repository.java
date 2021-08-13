package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.TempMark1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempMark1Repository extends JpaRepository<TempMark1, Integer>{
    List<TempMark1> findByTesterIdOrderByQuestionIdAsc(int testerId);
    List<TempMark1> findByTesterIdAndPageNoOrderByQuestionIdAsc(int testerId, int pageNo);
    TempMark1 findByTesterIdAndQuestionId(int testerId, int questionId);
    TempMark1 findFirstByTesterIdOrderByQuestionIdDesc(int testerId);
    void deleteByTesterId(int testerId);
}