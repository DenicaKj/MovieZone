package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectionIsPlayedInRoomRepository extends JpaRepository<ProjectionIsPlayedInRoom, ProjectionIsPlayedInRoomId> {
    List<ProjectionIsPlayedInRoom> findAllById_projection(Integer id_projection);
}
