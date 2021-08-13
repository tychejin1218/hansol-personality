package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Factor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Integer>{
    List<Factor> findByNormPersonalityId(int normPersonalityId);
}