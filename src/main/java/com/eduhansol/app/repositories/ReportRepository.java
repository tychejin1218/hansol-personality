package com.eduhansol.app.repositories;

import com.eduhansol.app.entities.Report;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>{
}