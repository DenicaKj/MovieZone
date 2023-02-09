package com.example.moviezone.service;

import com.example.moviezone.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerService {
    List<Customer> findAllCustomers();
    Optional<Customer> getCustomerById(int id);
}
