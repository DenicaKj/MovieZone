package com.example.moviezone.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@ToString
@Table(name = "workers")
@PrimaryKeyJoinColumn(name = "id_worker")
public class Worker extends User {

    String position;

    String work_hours_from;
    String work_hours_to;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    Cinema cinema;

    public Worker(String password, String first_name, String last_name, String address, String contact_number, String username) {
        super(password, first_name, last_name, address, contact_number, username);
    }

    public Worker() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_ADMIN);
    }

}
