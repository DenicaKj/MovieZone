package com.example.moviezone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Long id_category;
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
        return Objects.equals(id_category, that.id_category) && Objects.equals(name, that.name) && Objects.equals(extra_amount, that.extra_amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_category, name, extra_amount);
    }
}
