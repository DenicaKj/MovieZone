package com.example.moviezone.service.Impl;

import com.example.moviezone.repository.ProjectionIsPlayedInRoomRepository;
import com.example.moviezone.service.ProjectionIsPlayedInRoomService;
import org.springframework.stereotype.Service;

@Service
public class ProjectionIsPlayedInRoomServiceImpl implements ProjectionIsPlayedInRoomService {
    private final ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository;

    public ProjectionIsPlayedInRoomServiceImpl(ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository) {
        this.projectionIsPlayedInRoomRepository = projectionIsPlayedInRoomRepository;
    }

}
