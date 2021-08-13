package com.eduhansol.app.repositories;

import java.util.List;

import com.eduhansol.app.entities.Orientation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrientationRepository extends JpaRepository<Orientation, Integer>{
    List<Orientation> findByNormPersonalityId(int normPersonalityId);
    Orientation findByNormPersonalityIdAndPageNo(int normPersonalityId, int pageNo);
}