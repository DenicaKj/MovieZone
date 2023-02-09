package com.example.moviezone.service;

import com.example.moviezone.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories();
    Optional<Category> getCategoryById(int id);
}
