package com.eduhansol.app.repositories;

import java.util.List;
import com.eduhansol.app.entities.Hq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HqRepository extends JpaRepository<Hq, Integer>{
    List<Hq> findByIsDelete(boolean isDelete);
}