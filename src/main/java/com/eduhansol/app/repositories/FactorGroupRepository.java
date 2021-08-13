package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.FactorGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorGroupRepository extends JpaRepository<FactorGroup, Integer>{
    List<FactorGroup> findByNormPersonalityId(int normPersonalityId);
}