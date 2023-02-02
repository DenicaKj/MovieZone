package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.repository.Projection_RoomRepository;
import com.example.moviezone.service.Projection_RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Projection_RoomServiceImpl implements Projection_RoomService {
    private final Projection_RoomRepository projectionRoomRepository;

    public Projection_RoomServiceImpl(Projection_RoomRepository projectionRoomRepository) {
        this.projectionRoomRepository = projectionRoomRepository;
    }

    @Override
    public List<Projection_Room> findAllProjectionRooms() {
        return projectionRoomRepository.findAll();
    }
}
