package com.example.moviezone.service.Impl;

import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.repository.ProjectionIsPlayedInRoomRepository;
import com.example.moviezone.service.ProjectionIsPlayedInRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectionIsPlayedInRoomServiceImpl implements ProjectionIsPlayedInRoomService {
    private final ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository;

    public ProjectionIsPlayedInRoomServiceImpl(ProjectionIsPlayedInRoomRepository projectionIsPlayedInRoomRepository) {
        this.projectionIsPlayedInRoomRepository = projectionIsPlayedInRoomRepository;
    }

    @Override
    public List<ProjectionIsPlayedInRoom> getProjectionPlayedInRoom(Integer id) {
        return projectionIsPlayedInRoomRepository.findAllByProjectionId(id);
    }
}
