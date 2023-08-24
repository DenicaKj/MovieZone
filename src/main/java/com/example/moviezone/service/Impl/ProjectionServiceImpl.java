package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Discount;
import com.example.moviezone.model.Film;
import com.example.moviezone.model.Projection;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.repository.DiscountRepository;
import com.example.moviezone.repository.FilmRepository;
import com.example.moviezone.repository.ProjectionIsPlayedInRoomRepository;
import com.example.moviezone.repository.ProjectionRepository;
import com.example.moviezone.service.ProjectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectionServiceImpl implements ProjectionService {
    private final ProjectionRepository projectionRepository;
    private final ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository;
    private final FilmRepository filmRepository;
    private final DiscountRepository discountRepository;
    public ProjectionServiceImpl(ProjectionRepository projectionRepository, ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository, FilmRepository filmRepository, DiscountRepository discountRepository) {
        this.projectionRepository = projectionRepository;
        this.projectionIsPlayedInRoomRepository = projectionIsPlayedInRoomRepository;
        this.filmRepository = filmRepository;
        this.discountRepository = discountRepository;
    }

    @Override
    public List<Projection> findAllProjections() {
        return projectionRepository.findAll();
    }

    @Override
    public List<Projection> getProjectionsForFilms(int id) {
        return projectionRepository.getProjectionsForFilms(id);
    }

    @Override
    public List<Projection> getProjectionsNow() {
        return projectionRepository.getProjectionsNow();
    }

    @Override
    public Projection findById(Integer id_projection) {
        return projectionRepository.findById(id_projection).orElseThrow(RuntimeException::new);
    }


    @Override
    public Projection save(LocalDateTime date_time_start, LocalDateTime date_time_end, String type_of_technology, Integer id_film, Integer id_room, Integer id_discount) {
       Film film=filmRepository.findById(id_film).orElseThrow(RuntimeException::new);
        Discount discount = discountRepository.findById(id_discount).orElseThrow(RuntimeException::new);
        Projection projection =  projectionRepository.save(new Projection(date_time_start,type_of_technology,date_time_end,film,discount));
        projectionIsPlayedInRoomRepository.save(new ProjectionIsPlayedInRoom(projection.getId_projection(),id_room));
        return projection;
    }

}
