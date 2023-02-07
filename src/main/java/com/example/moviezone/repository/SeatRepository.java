package com.example.moviezone.repository;

import com.example.moviezone.model.Projection_Room;
import com.example.moviezone.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Integer> {
    List<Seat> findAllByProjection_room(Projection_Room projection_room);
}
