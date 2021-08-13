package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{
    List<Region> findByHqId(int hqId);
    List<Region> findByHqIdAndIsDelete(int hqId, boolean isDelete);
}