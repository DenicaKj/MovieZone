package com.example.moviezone.service;

import com.example.moviezone.model.Category;
import com.example.moviezone.model.Projection;
import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<Seat> findAllSeats();
    List<Seat> findAllByProjection_Room(Projection_Room projection_room);
    List<Seat> findAllByRoomAndCategory(Projection projection, Projection_Room projectionRoom, Category category);
    Optional<Seat> getSeatById(int id);
}
