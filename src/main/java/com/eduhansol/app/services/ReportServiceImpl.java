package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Report;
import com.eduhansol.app.repositories.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository _reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        _reportRepository = reportRepository;
    }

    @Override
    public List<Report> getList() {
        return _reportRepository.findAll();
    }

    @Override
    public Report get(int id) {
        return _reportRepository.findById(id).get();
    }
}