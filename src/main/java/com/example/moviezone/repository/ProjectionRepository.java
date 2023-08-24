package com.example.moviezone.repository;

import com.example.moviezone.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@Transactional
public interface ProjectionRepository extends JpaRepository<Projection,Integer> {
    @Procedure("project.getProjectionsForFilms")
    List<Projection> getProjectionsForFilms(int id);
    @Procedure("project.getProjectionsNow")
    List<Projection> getProjectionsNow();
}
