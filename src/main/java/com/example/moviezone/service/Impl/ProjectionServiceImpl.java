package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Projection;
import com.example.moviezone.repository.ProjectionRepository;
import com.example.moviezone.service.ProjectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectionServiceImpl implements ProjectionService {
    private final ProjectionRepository projectionRepository;

    public ProjectionServiceImpl(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    @Override
    public List<Projection> findAllProjections() {
        return projectionRepository.findAll();
    }

    @Override
    public List<Projection> findAllAvailableProjections(LocalDate date) {
        return projectionRepository.findAllByDate_time_startBefore(date);
    }

}
