package com.poly.admin.repository;

import com.poly.admin.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetail, Integer> {
    @Override
    List<ProductDetail> findAll();
    List<ProductDetail> findByProductId(int productId);
}
