package com.example.moviezone.repository;


import com.example.moviezone.model.Work_Hours_Weekly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Work_Hours_WeeklyRepository extends JpaRepository<Work_Hours_Weekly,Integer> {
}
