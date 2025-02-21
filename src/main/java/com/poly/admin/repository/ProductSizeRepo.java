package com.poly.admin.repository;

import com.poly.admin.model.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepo extends JpaRepository<ProductSize, Integer> {
    List<ProductSize> findByProductDetail_Id(int productId);
}
