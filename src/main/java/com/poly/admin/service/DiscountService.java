package com.poly.admin.service;

import com.poly.admin.model.ProductDiscount;
import com.poly.admin.repository.DiscountRepo;
import jakarta.persistence.EntityNotFoundException;
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

    public ProductDiscount updateProductDiscount(ProductDiscount productDiscount) {
        ProductDiscount discount = discountRepo.findById(productDiscount.getId()).orElseThrow(
                () -> new EntityNotFoundException("Product discount not found")
        );
        discount.setDiscountRate(productDiscount.getDiscountRate());
        return discountRepo.save(discount);
    }

    public ProductDiscount addProductDiscount(ProductDiscount productDiscount) {
        return discountRepo.save(productDiscount);
    }
}
