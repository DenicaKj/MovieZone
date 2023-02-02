package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id_user;
    String password;
    String first_name;
    String last_name;
    String address;
    String contact_number;
    LocalDateTime date_created;


    public User(Integer id_user, String password, String first_name, String last_name, String address, String contact_number, LocalDateTime date_created) {
        this.id_user = id_user;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.contact_number = contact_number;
        this.date_created = date_created;
    }

    public User() {

    }
}
