package com.example.moviezone.service;

import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findAllSeats();
    List<Seat> findAllByProjection_Room(Projection_Room projection_room);
}
