package com.poly.admin.service;

import com.poly.admin.model.ProductDiscount;
import com.poly.admin.repository.DiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepo discountRepo;

    public List<ProductDiscount> getAllColorDiscount() {
        return discountRepo.findAll();
    }

    public ProductDiscount getProductDiscountById(int id) {
        return discountRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Product discount not found")
        );
    }
}
