package com.example.moviezone.repository;

import com.example.moviezone.model.views.CustomerCinemaReport;
import com.example.moviezone.model.views.CustomerCinemaReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerCinemaReportRepository  extends JpaRepository<CustomerCinemaReport, CustomerCinemaReportId> {
}
