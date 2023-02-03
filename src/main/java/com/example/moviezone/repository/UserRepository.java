package com.example.moviezone.repository;


import com.example.moviezone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);
    List<User> findAllByUsernameAndPassword(String username, String password);
}
