package com.example.moviezone.model.manytomany;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "projection_is_played_in_room")
@IdClass(ProjectionIsPlayedInRoomId.class)
public class ProjectionIsPlayedInRoom {
    @Id
    @Column(name ="id_projection")
    Integer id_projection;

    @Id
    @Column(name ="id_room")
    Integer id_room;


}
