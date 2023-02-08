package com.example.moviezone.model.procedures;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
public class FilmsReturnTable {
    int id_film;
    LocalDate start_date;
    String name;

    public FilmsReturnTable(Integer id_film, LocalDate start_date, String name) {
        this.id_film = id_film;
        this.start_date = start_date;
        this.name = name;
    }

    public int getId_film() {
        return id_film;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public String getName() {
        return name;
    }
}
