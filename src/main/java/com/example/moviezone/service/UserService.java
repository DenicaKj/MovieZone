package com.example.moviezone.service;

import com.example.moviezone.model.Role;
import com.example.moviezone.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findById(Integer user_id);
    User findByUsername(String username);

    void register(String first_name, String last_name,String username, String email, String number, String password, Role role);
    User login(String username,String password);
}
