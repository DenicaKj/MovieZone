package com.example.moviezone.service.Impl;

import com.example.moviezone.model.Discount;
import com.example.moviezone.repository.DiscountRepository;
import com.example.moviezone.service.DiscountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Discount save(String code, String type, LocalDate validity, Integer percent) {
        return discountRepository.save(new Discount(validity,code,type,percent));
    }

    @Override
    public List<Discount> getValidDiscounts() {
        return discountRepository.getValidDiscounts();
    }
}
