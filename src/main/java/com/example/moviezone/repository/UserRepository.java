package com.example.moviezone.repository;


import com.example.moviezone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    List<User> findAllByUsernameAndPassword(String username, String password);
}
