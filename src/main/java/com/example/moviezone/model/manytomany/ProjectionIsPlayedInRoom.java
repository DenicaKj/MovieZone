package com.example.moviezone.model.manytomany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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
    Integer idprojection;

    @Id
    @Column(name ="id_room")
    Integer idroom;


}
