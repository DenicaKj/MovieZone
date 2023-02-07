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
@Table(name = "`cinema_organizes_event`")
@IdClass(CinemaOrganizesEventId.class)
public class CinemaOrganizesEvent {
    @Id
    @Column(name = "id_cinema")
    Integer id_cinema;
    @Column(name = "id_event")
    @Id
    Integer id_event;

    public CinemaOrganizesEvent(Integer id_cinema, Integer id_event) {
        this.id_cinema=id_cinema;
        this.id_event=id_event;
    }
}
