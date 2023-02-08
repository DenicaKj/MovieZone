package com.example.moviezone.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProjectionIsPlayedInRoomId implements Serializable {
    Integer idprojection;
    Integer idroom;
}
