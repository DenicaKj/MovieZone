package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Customer;
import com.example.moviezone.model.Event;
import com.example.moviezone.model.manytomany.CustomerIsInterestedInEvent;
import com.example.moviezone.repository.CustomerIsInterestedInEventRepository;
import com.example.moviezone.service.CustomerIsInterestedInEventService;
import org.springframework.stereotype.Service;

@Service
public class CustomerIsInterestedInEventImpl implements CustomerIsInterestedInEventService {
    private final CustomerIsInterestedInEventRepository customerIsInterestedInEventRepository;

    public CustomerIsInterestedInEventImpl(CustomerIsInterestedInEventRepository customerIsInterestedInEventRepository) {
        this.customerIsInterestedInEventRepository = customerIsInterestedInEventRepository;
    }

    @Override
    public CustomerIsInterestedInEvent add(Integer id_customer, Integer id_event) {
        CustomerIsInterestedInEvent customerIsInterestedInEvent=new CustomerIsInterestedInEvent(id_customer,id_event);
        return customerIsInterestedInEventRepository.save(customerIsInterestedInEvent);
    }

    @Override
    public void delete(Customer customer, Event event) {
        CustomerIsInterestedInEvent customerIsInterestedInEvent=findByCustomerAndEvent(customer,event);
        customerIsInterestedInEventRepository.delete(customerIsInterestedInEvent);
    }

    @Override
    public CustomerIsInterestedInEvent findByCustomerAndEvent(Customer customer, Event event) {
        return customerIsInterestedInEventRepository.findFirstByIdeventAndAndIdcustomer(event.getId_event(),customer.getId_user());
    }
}
