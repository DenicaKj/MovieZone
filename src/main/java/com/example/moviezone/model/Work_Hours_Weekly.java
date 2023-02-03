package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "work_hours_weekly")
public class Work_Hours_Weekly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_work_hours;

    Integer week_number;

    String year_num;

    LocalDateTime hours_from;

    LocalDateTime hours_to;

    Boolean check_in;

    @ManyToOne
    @JoinColumn(name = "id_worker")
    Worker worker;
}
