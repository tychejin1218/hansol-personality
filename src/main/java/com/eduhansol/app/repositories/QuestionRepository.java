package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{
    List<Question> findByNormPersonalityId(int normPersonalityId);
    List<Question> findByNormPersonalityIdAndPageNo(int normPersonalityId, int pageNo);
}