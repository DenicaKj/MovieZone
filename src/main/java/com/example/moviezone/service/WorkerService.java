package com.example.moviezone.service;

import com.example.moviezone.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> findAllWorkers();
    Worker getWorkerByUsername(String username);
}
