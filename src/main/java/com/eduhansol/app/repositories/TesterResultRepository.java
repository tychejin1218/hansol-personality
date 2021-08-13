package com.eduhansol.app.repositories;

import com.eduhansol.app.entities.TesterResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesterResultRepository extends JpaRepository<TesterResult, Integer>
{
    TesterResult findByTesterId(int testerId);
    void deleteByTesterId(int testerId);
}