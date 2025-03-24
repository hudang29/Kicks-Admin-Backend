package com.poly.admin.controller;

import com.poly.admin.model.ProductDiscount;
import com.poly.admin.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff/api")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping("/product-discount")
    public ResponseEntity<List<ProductDiscount>> getProductDiscount() {
        List<ProductDiscount> discounts = discountService.getAllColorDiscount();
        return discounts.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(discounts);
    }

    @GetMapping("/product-discount/{id}")
    public ResponseEntity<ProductDiscount> getProductDiscountById(@PathVariable int id) {
        ProductDiscount discount = discountService.getProductDiscountById(id);
        return discount != null ? ResponseEntity.ok(discount) : ResponseEntity.notFound().build();
    }

    @PutMapping("/discount/update")
    public ResponseEntity<?> updateProductDiscount(@RequestBody ProductDiscount productDiscount) {
        try {
            ProductDiscount discount = discountService.updateProductDiscount(productDiscount);
            return ResponseEntity.ok(discount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/discount/add")
    public ResponseEntity<?> addProductDiscount(@RequestBody ProductDiscount productDiscount) {
        try {
            ProductDiscount discount = discountService.addProductDiscount(productDiscount);
            return ResponseEntity.ok(discount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
