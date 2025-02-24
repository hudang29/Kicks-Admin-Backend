package com.poly.admin.controller;

import com.poly.admin.model.ProductDiscount;
import com.poly.admin.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/api/product-discount")
    public List<ProductDiscount> getProductDiscount() {
        return discountService.getAllColorDiscount();
    }
}
