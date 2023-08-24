package com.example.moviezone.service;

import com.example.moviezone.model.Discount;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface DiscountService {
    Discount save(String code, String type, LocalDate validity, Integer percent);
    List<Discount> getValidDiscounts();
}
