package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.User;
import com.example.moviezone.model.Worker;
import com.example.moviezone.model.exceptions.UserNotFoundException;
import com.example.moviezone.repository.UserRepository;
import com.example.moviezone.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(String first_name, String last_name, String username, String email, String number, String password, String role) {
        if(role.equals("worker"))
        {
            return userRepository.save(new Worker(passwordEncoder.encode(password),first_name,last_name,username,email,number));
        }
        else
            return userRepository.save(new Customer(passwordEncoder.encode(password),first_name,last_name,username,email,number));
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findAllByUsernameAndPassword(username,password).stream().findFirst().orElseThrow(UserNotFoundException::new);
    }


}
