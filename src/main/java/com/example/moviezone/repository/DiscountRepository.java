package com.example.moviezone.repository;

import com.example.moviezone.model.Discount;
import com.example.moviezone.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface DiscountRepository extends JpaRepository<Discount,Integer> {
    @Procedure("project.getValidDiscounts")
    List<Discount> getValidDiscounts();
}
