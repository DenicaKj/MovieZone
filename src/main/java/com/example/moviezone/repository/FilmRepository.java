package com.example.moviezone.repository;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.procedures.FilmsReturnTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Transactional
public interface FilmRepository extends JpaRepository<Film,Integer> {
    @Procedure("project.getFilmsFromCinema1")
    List<Film> getFilmsFromCinema(int id);
    @Procedure("project.getFilmsFromCinemaNow")
    List<Film> getFilmsFromCinemaNow(int id);
    @Procedure("project.getFilmsNow")
    List<Film> getFilmsNow();
}