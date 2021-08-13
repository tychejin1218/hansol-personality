package com.eduhansol.app.repositories;

import com.eduhansol.app.entities.Group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupCustomRepository {
    Page<Group> findAllByRegionId(int regionId, Pageable pageable);
    Page<Group> findAllByHqId(int hqId, Pageable pageable);
    long countByRegionId(int regionId);
    long countByHqId(int hqId);
}