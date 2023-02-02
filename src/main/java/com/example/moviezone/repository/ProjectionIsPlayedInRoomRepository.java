package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionIsPlayedInRoomRepository extends JpaRepository<ProjectionIsPlayedInRoom, ProjectionIsPlayedInRoomId> {
}
