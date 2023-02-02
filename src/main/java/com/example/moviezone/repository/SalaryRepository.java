package com.example.moviezone.repository;

import com.example.moviezone.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary,Integer> {
}
