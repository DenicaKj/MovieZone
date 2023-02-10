package com.example.moviezone.service;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Event;
import com.example.moviezone.model.manytomany.CustomerIsInterestedInEvent;

import javax.persistence.criteria.CriteriaBuilder;

public interface CustomerIsInterestedInEventService {
 CustomerIsInterestedInEvent add(Integer id_customer,Integer id_event);
 void delete(Customer customer, Event event);
 CustomerIsInterestedInEvent findByCustomerAndEvent(Customer customer, Event event);
}
