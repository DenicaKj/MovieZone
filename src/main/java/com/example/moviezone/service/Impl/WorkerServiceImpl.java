package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Worker;
import com.example.moviezone.repository.WorkerRepository;
import com.example.moviezone.service.WorkerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public List<Worker> findAllWorkers() {
        return workerRepository.findAll();
    }
}
