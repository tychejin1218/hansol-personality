package com.eduhansol.app.repositories;

import com.eduhansol.app.entities.Mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer>{
    Mark findByTesterId(int testerId);
}