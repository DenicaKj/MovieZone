package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectionIsPlayedInRoomRepository extends JpaRepository<ProjectionIsPlayedInRoom, ProjectionIsPlayedInRoomId> {
    @Query("SELECT pir FROM ProjectionIsPlayedInRoom pir WHERE pir.idprojection = :id_projection")
    List<ProjectionIsPlayedInRoom> findAllByProjectionId(@Param("id_projection") Integer id_projection);
}
