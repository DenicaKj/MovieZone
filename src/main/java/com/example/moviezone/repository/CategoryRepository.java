package com.example.moviezone.repository;

import com.example.moviezone.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Optional<Category> getByIdcategory(int id);
}
