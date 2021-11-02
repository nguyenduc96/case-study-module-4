package com.group3.services.report;

import com.group3.models.report.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportService implements IReportService {
    @Override
    public Iterable<Report> findAll() {
        return null;
    }

    @Override
    public Page<Report> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Report> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Report save(Report report) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
