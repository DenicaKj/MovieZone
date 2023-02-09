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


import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id_category", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idcategory;
    String name;
    Integer extra_amount;

    public Category(String name, Integer extra_amount) {
        this.name = name;
        this.extra_amount = extra_amount;
    }

    public Category() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(idcategory, that.idcategory) && Objects.equals(name, that.name) && Objects.equals(extra_amount, that.extra_amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idcategory, name, extra_amount);
    }
}
