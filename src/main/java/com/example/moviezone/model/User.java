package com.example.moviezone.model;


import lombok.Getter;
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
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_user;
    String password;
    String first_name;
    String last_name;
    String address;
    String contact_number;
    String username;
    LocalDate date_created;


    public User(Integer id_user, String password, String first_name, String last_name, String address, String contact_number, String username, LocalDate date_created) {
        this.id_user = id_user;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.contact_number = contact_number;
        this.username = username;
        this.date_created = date_created;
    }

    public User(String password, String first_name, String last_name, String address, String contact_number, String username) {
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.contact_number = contact_number;
        this.username = username;
        this.date_created=LocalDate.now();
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
