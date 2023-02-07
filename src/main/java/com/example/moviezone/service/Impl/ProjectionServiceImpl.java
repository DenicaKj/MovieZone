package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.Projection;
import com.example.moviezone.repository.FilmRepository;
import com.example.moviezone.repository.ProjectionRepository;
import com.example.moviezone.service.ProjectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectionServiceImpl implements ProjectionService {
    private final ProjectionRepository projectionRepository;
    private final FilmRepository filmRepository;
    public ProjectionServiceImpl(ProjectionRepository projectionRepository, FilmRepository filmRepository) {
        this.projectionRepository = projectionRepository;
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Projection> findAllProjections() {
        return projectionRepository.findAll();
    }

    @Override
    public Projection findById(Integer id_projection) {
        return projectionRepository.findById(id_projection).orElseThrow(RuntimeException::new);
    }


    @Override
    public Projection save(LocalDate date_time_start, LocalDate date_time_end, String type_of_technology, Integer id_film) {
       Film film=filmRepository.findById(id_film).orElseThrow(RuntimeException::new);
        return projectionRepository.save(new Projection(date_time_start,type_of_technology,date_time_end,film));
    }

}
