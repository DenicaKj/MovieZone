package com.example.moviezone.repository;

import com.example.moviezone.model.Projection;
import com.example.moviezone.model.Projection_Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface Projection_RoomRepository extends JpaRepository<Projection_Room,Integer> {
    @Procedure("project.getRoomsForProjection")
    List<Projection_Room> getRoomsForProjection(int id);
}
