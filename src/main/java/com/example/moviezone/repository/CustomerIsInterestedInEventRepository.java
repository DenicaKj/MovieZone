package com.example.moviezone.repository;

import com.example.moviezone.model.manytomany.CustomerIsInterestedInEvent;
import com.example.moviezone.model.manytomany.CustomerIsInterestedInEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerIsInterestedInEventRepository extends JpaRepository<CustomerIsInterestedInEvent, CustomerIsInterestedInEventId> {
}
