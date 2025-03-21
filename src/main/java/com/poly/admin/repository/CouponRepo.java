package com.poly.admin.repository;

import com.poly.admin.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    boolean existsByCouponCode(String couponCode);
}
