package com.example.moviezone.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_salary;

    Integer sum;

    LocalDate date_from;
    LocalDate date_to;

    @ManyToOne
    @JoinColumn(name = "id_worker")
    Worker worker;
}
