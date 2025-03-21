package com.poly.admin.controller;

import com.poly.admin.model.Coupon;
import com.poly.admin.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/staff/api/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/add")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon) {
        Coupon createdCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createdCoupon);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Integer id, @RequestBody Coupon coupon) {
        Coupon updatedCoupon = couponService.updateCoupon(id, coupon);
        return ResponseEntity.ok(updatedCoupon);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Coupon>> getAllCoupons() {
        List<Coupon> coupons = couponService.getAllCoupons();
        return ResponseEntity.ok(coupons);
    }
}
