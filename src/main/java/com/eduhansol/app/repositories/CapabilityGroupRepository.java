package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.CapabilityGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityGroupRepository extends JpaRepository<CapabilityGroup, Integer>{
    List<CapabilityGroup> findByNormPersonalityId(int normPersonalityId);
}