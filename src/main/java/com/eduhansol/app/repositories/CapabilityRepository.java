package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Capability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapabilityRepository extends JpaRepository<Capability, Integer>{
    List<Capability> findByNormPersonalityId(int normPersonalityId);
}