package com.example.moviezone.service;

import com.example.moviezone.model.Film;
import com.example.moviezone.model.manytomany.ProjectionIsPlayedInRoom;

import java.util.List;
import java.util.Optional;

public interface ProjectionIsPlayedInRoomService {
    List<ProjectionIsPlayedInRoom> getProjectionPlayedInRoom(Integer id);

}
