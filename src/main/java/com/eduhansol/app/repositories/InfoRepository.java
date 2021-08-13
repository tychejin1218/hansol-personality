package com.eduhansol.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduhansol.app.entities.Info;

@Repository
public interface InfoRepository extends JpaRepository<Info, Integer>{
}