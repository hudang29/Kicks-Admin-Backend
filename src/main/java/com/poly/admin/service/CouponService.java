package com.poly.admin.service;

import com.poly.admin.model.Coupon;
import com.poly.admin.repository.CouponRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CouponService {
    @Autowired
    private CouponRepo couponRepository;

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Transactional
    public Coupon createCoupon(Coupon coupon) {
        if (couponRepository.existsByCouponCode(coupon.getCouponCode())) {
            throw new IllegalArgumentException("Coupon code already exists");
        }
        coupon.setCreatedAt(Instant.now());
        return couponRepository.save(coupon);
    }

    @Transactional
    public Coupon updateCoupon(Integer id, Coupon couponDetails) {
        Optional<Coupon> optionalCoupon = couponRepository.findById(id);
        if (optionalCoupon.isEmpty()) {
            throw new IllegalArgumentException("Coupon not found");
        }
        Coupon coupon = optionalCoupon.get();
        coupon.setName(couponDetails.getName());
        coupon.setCouponCode(couponDetails.getCouponCode());
        coupon.setDescription(couponDetails.getDescription());
        coupon.setDiscountRate(couponDetails.getDiscountRate());
        coupon.setStartDate(couponDetails.getStartDate());
        coupon.setEndDate(couponDetails.getEndDate());
        coupon.setUsageLimit(couponDetails.getUsageLimit());
        return couponRepository.save(coupon);
    }

}
