package com.example.moviezone.service;

import com.example.moviezone.model.Projection_Room;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface Projection_RoomService {
    List<Projection_Room> findAllProjectionRooms();

    List<Projection_Room> getRoomByProjection(int id);
}
