package com.poly.admin.repository;

import com.poly.admin.model.ProductDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<ProductDiscount, Integer> {

}
