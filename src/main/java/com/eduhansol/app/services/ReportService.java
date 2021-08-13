package com.eduhansol.app.services;

import java.util.List;

import com.eduhansol.app.entities.Report;

public interface ReportService {
    List<Report> getList();
    Report get(int id);
}