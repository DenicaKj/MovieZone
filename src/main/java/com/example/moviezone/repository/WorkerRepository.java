package com.example.moviezone.repository;

import com.example.moviezone.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Integer> {
    Worker getByUsername(String username);
}
