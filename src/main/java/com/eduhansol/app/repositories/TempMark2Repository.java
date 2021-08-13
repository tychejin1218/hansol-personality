package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.TempMark2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempMark2Repository extends JpaRepository<TempMark2, Integer>{
    List<TempMark2> findByTesterIdOrderByQuestionIdAsc(int testerId);
    List<TempMark2> findByTesterIdAndPageNoOrderByQuestionIdAsc(int testerId, int pageNo);
    List<TempMark2> findByTesterIdAndQuestionSetIdAndMark2(int testerId, int questionSetId, String mark2);
    // TempMark2 findByTesterIdAndQuestionSetIdAndMark2(int testerId, int questionSetId, String mark2);
    TempMark2 findByTesterIdAndQuestionId(int testerId, int questionId);
    TempMark2 findByTesterIdAndQuestionIdAndMark2(int testerId, int questionId, String mark2);
    void deleteByTesterId(int testerId);
    void deleteByTesterIdAndQuestionSetId(int testerId, int questionSetId);
    void deleteByTesterIdAndQuestionSetIdAndMark2(int testerId, int questionSetId, String mark2);
    void deleteByTesterIdAndQuestionId(int testerId, int questionId);
}