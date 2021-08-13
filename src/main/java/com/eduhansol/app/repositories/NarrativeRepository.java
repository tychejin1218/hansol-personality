package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Narrative;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarrativeRepository extends JpaRepository<Narrative, Integer>{
    List<Narrative> findBySubFactorId(int subFactorId);
}