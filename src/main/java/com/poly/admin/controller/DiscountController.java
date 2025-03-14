package com.poly.admin.controller;

import com.poly.admin.model.ProductDiscount;
import com.poly.admin.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/api/product-discount")
    public ResponseEntity<List<ProductDiscount>> getProductDiscount() {
        List<ProductDiscount> discounts = discountService.getAllColorDiscount();
        return discounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(discounts);
    }

    @GetMapping("/api/product-discount/{id}")
    public ResponseEntity<ProductDiscount> getProductDiscountById(@PathVariable int id) {
        ProductDiscount discount = discountService.getProductDiscountById(id);
        return discount != null ? ResponseEntity.ok(discount) : ResponseEntity.notFound().build();
    }

}
