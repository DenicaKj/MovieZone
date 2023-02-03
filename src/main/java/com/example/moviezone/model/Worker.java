package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

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

    @ManyToOne()
    Cinema cinema;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_ADMIN);
    }

}
