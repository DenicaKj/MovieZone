package com.example.moviezone.service;

import com.example.moviezone.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findById(Integer user_id);
    User findByUsername(String username);
}
